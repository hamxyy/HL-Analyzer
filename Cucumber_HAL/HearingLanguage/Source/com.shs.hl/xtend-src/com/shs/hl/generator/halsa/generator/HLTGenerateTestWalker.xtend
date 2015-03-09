package com.shs.hl.generator.halsa.generator

import com.shs.common.commonLanguage.EnumLiteral
import com.shs.common.commonLanguage.EnumParameter
import com.shs.hl.generator.halsa.HLTSyntaxTreeWalkerBase
import com.shs.hl.generator.halsa.evaluation.LRValueSwitch
import com.shs.hl.generator.halsa.struct.Constant
import com.shs.hl.generator.halsa.struct.ExpressionResult
import com.shs.hl.generator.halsa.struct.GlobalVariable
import com.shs.hl.generator.halsa.struct.HLTBoolExpression
import com.shs.hl.generator.halsa.struct.HLTCompareRelation
import com.shs.hl.generator.halsa.struct.HLTConditionNode
import com.shs.hl.generator.halsa.struct.HLTConstantExpression
import com.shs.hl.generator.halsa.struct.HLTControlFlowTree
import com.shs.hl.generator.halsa.struct.HLTControlFlowTreeNode
import com.shs.hl.generator.halsa.struct.HLTDummyBoolExpression
import com.shs.hl.generator.halsa.struct.HLTExecutionNode
import com.shs.hl.generator.halsa.struct.LocalVariable
import com.shs.hl.generator.halsa.struct.StorageObject
import com.shs.hl.hearingLanguage.And
import com.shs.hl.hearingLanguage.ArgList
import com.shs.hl.hearingLanguage.AssignmentStatement
import com.shs.hl.hearingLanguage.Equals
import com.shs.hl.hearingLanguage.Expression
import com.shs.hl.hearingLanguage.FunctionDeclaration
import com.shs.hl.hearingLanguage.Greater
import com.shs.hl.hearingLanguage.GreaterOrEquals
import com.shs.hl.hearingLanguage.IfStatement
import com.shs.hl.hearingLanguage.LocalVariableDeclaration
import com.shs.hl.hearingLanguage.LocalVariableDeclarationStatement
import com.shs.hl.hearingLanguage.Namespace
import com.shs.hl.hearingLanguage.NumberLiteral
import com.shs.hl.hearingLanguage.Or
import com.shs.hl.hearingLanguage.ParameterDeclaration
import com.shs.hl.hearingLanguage.ParameterReadExpression
import com.shs.hl.hearingLanguage.ReturnStatement
import com.shs.hl.hearingLanguage.Smaller
import com.shs.hl.hearingLanguage.SmallerOrEquals
import com.shs.hl.hearingLanguage.SwitchStatement
import com.shs.hl.hearingLanguage.UnEquals
import java.util.ArrayList
import java.util.HashMap
import java.util.Map

class HLTGenerateTestWalker extends HLTSyntaxTreeWalkerBase
{
	HLTControlFlowTreeNode curNode
	HLTLocalVariables localVarHandler = new HLTLocalVariables
	ExpressionResult returnValue
	StorageObject lValue
	ExpressionResult rValue

	HLTTestSuiteFactory factory = new HLTTestSuiteFactory

	override walkApplicationMacro(Namespace macro)
	{
		factory.forMacro(macro.name)
		super.walkApplicationMacro(macro)
	}

	override walkFunction(FunctionDeclaration declaration)
	{
		var controlTree = HLTControlFlowTree.create
		curNode = controlTree.root

		super.walkFunction(declaration)

		val s = controlTree.format
		val args = new ArrayList<String>
		declaration.args.forEach[a|args.add(a.name)]
		factory.forFunction(declaration.name, args, controlTree)

	}

	override walkFunctionCall(FunctionDeclaration function, ArgList argList)
	{
		val funcWalker = new HLTGenerateTestWalker()
		funcWalker.walkFunction(function)
		if (funcWalker.returnValue != null)
		{
			rValue = returnValue
		}

	//		// Keep the context
	//		val curNode = curNode
	//		val curBoolExpr = curBoolExpr
	//		val lValue = lValue
	//		val rValue = rValue
	//		// Keep the context
	//		this.curNode = curNode
	//		this.curBoolExpr = curBoolExpr
	//		this.lValue = lValue
	//		this.rValue = rValue
	}

	override walkReturnStatement(ReturnStatement returnStmt)
	{
		lrValueSwitch = LRValueSwitch.RValue
		super.walkReturnStatement(returnStmt)
		returnValue = rValue
	}

	override walkIfStatement(IfStatement ifStmt)
	{
		super.walkExpression(ifStmt.condition)
		val ifCondition = curBoolExpr
		if (ifCondition instanceof HLTConstantExpression)
		{
			if (ifCondition.evaluate() == true)
			{

				// Directly walk the thenBody because the condition is always true.
				super.walkStatementList(ifStmt.thenBody)
			}
			else
			{
				throw new Exception("Unreachable statements!")
			}
		}
		else
		{
			var ifConditionNode = new HLTConditionNode(curBoolExpr)
			curNode.append(ifConditionNode)
			curNode = ifConditionNode
			super.walkStatementList(ifStmt.thenBody)
			curNode = curNode.parent
		}

		for (elseIf : ifStmt.elseIfs)
		{
			super.walkIfCondition(elseIf.condition)
			val elseIfCondition = ifCondition.not.and(curBoolExpr)
			var elseIfConditionNdoe = new HLTConditionNode(elseIfCondition)
			curNode.append(elseIfConditionNdoe)
			curNode = elseIfConditionNdoe
			super.walkStatementList(elseIf.body)
			curNode = curNode.parent
		}

		if (ifStmt.elseBody != null)
		{
			val elseCondition = ifCondition.not
			var elseConditionNode = new HLTConditionNode(elseCondition)
			curNode.append(elseConditionNode)
			curNode = elseConditionNode
			super.walkStatementList(ifStmt.elseBody)
			curNode = curNode.parent
		}
	}

	override walkSwitchStatement(SwitchStatement switchStmt)
	{
		this.lrValueSwitch = LRValueSwitch.RValue
		walkExpression(switchStmt.expr)
		val leftOperand = rValue

		var HLTBoolExpression defaultCondition = new HLTDummyBoolExpression
		for (eachCase : switchStmt.cases)
		{
			this.lrValueSwitch = LRValueSwitch.RValue
			walkExpression(eachCase.expr)
			val rightOperand = rValue

			val caseCondition = makeCompareExpression(leftOperand, rightOperand, HLTCompareRelation.Equals)
			defaultCondition = defaultCondition.and(caseCondition.not)
			var caseConditionNode = new HLTConditionNode(caseCondition)
			curNode.append(caseConditionNode)
			curNode = caseConditionNode
			super.walkStatementList(eachCase.body)
			curNode = curNode.parent
		}

		if (switchStmt.defaultBlock != null)
		{
			var defaultConditionNode = new HLTConditionNode(defaultCondition)
			curNode.append(defaultConditionNode)
			curNode = defaultConditionNode
			super.walkStatementList(switchStmt.defaultBlock)
			curNode = curNode.parent
		}
	}

	override walkLocalVariableDeclarationStatement(LocalVariableDeclarationStatement statement)
	{
		var localVarDeclaration = statement.^var as LocalVariableDeclaration
		localVarHandler.declare(localVarDeclaration.name)
		walkExpression(localVarDeclaration.init as Expression)

		val localVar = localVarHandler.getVariable(localVarDeclaration.name)
		localVar.assign(rValue, curNode.conditionsAlongPath)
	}

	override walkAssignmentStatement(AssignmentStatement assign)
	{
		lrValueSwitch = LRValueSwitch.LValue
		walkExpression(assign.lvalue)
		lrValueSwitch = LRValueSwitch.RValue
		walkExpression(assign.rvalue)

		if (lValue.isGlobal)
		{
			var HLTExecutionNode node = new HLTExecutionNode(lValue.name, (rValue as GlobalVariable).name)
			curNode.append(node)
		}
		else
		{
			val newValue = rValue
			val oldVar = localVarHandler.getVariable(lValue.name)
			oldVar.assign(newValue, curNode.conditionsAlongPath)
		}
	}

	override walkAndExpression(And andExpr)
	{
		super.walkExpression(andExpr.left)
		val left = curBoolExpr
		super.walkExpression(andExpr.right)
		val right = curBoolExpr
		curBoolExpr = left.and(right)
	}

	override walkOrExpression(Or orExpr)
	{
		super.walkExpression(orExpr.left)
		val left = curBoolExpr
		super.walkExpression(orExpr.right)
		val right = curBoolExpr
		curBoolExpr = left.or(right)
	}

	override walkEqualsExpression(Equals equals)
	{
		walkCompareLevel(equals.left, equals.right, HLTCompareRelation.Equals)
	}

	override walkUnEqualsExpression(UnEquals unEquals)
	{
		walkCompareLevel(unEquals.left, unEquals.right, HLTCompareRelation.Unequals)
	}

	override walkGreaterExpression(Greater greater)
	{
		walkCompareLevel(greater.left, greater.right, HLTCompareRelation.GreaterThan)
	}

	override walkSmallerExpression(Smaller smaller)
	{
		walkCompareLevel(smaller.left, smaller.right, HLTCompareRelation.LessThan)
	}

	override walkGreaterOrEqualsExpression(GreaterOrEquals greaterOrEquals)
	{
		walkCompareLevel(greaterOrEquals.left, greaterOrEquals.right, HLTCompareRelation.GreaterThanOrEquals)
	}

	override walkSmallerOrEqualsExpression(SmallerOrEquals smallerOrEquals)
	{
		walkCompareLevel(smallerOrEquals.left, smallerOrEquals.right, HLTCompareRelation.LessThanOrEquals)
	}

	def private walkCompareLevel(Expression leftExpr, Expression rightExpr, HLTCompareRelation rel)
	{
		lrValueSwitch = LRValueSwitch.RValue
		walkExpression(leftExpr)
		val leftOperand = rValue
		lrValueSwitch = LRValueSwitch.RValue
		walkExpression(rightExpr)
		val rightOperand = rValue

		var expr = makeCompareExpression(leftOperand, rightOperand, rel)

		curBoolExpr = expr
	}

	private def HLTBoolExpression makeCompareExpression(ExpressionResult leftOperand, ExpressionResult rightOperand,
		HLTCompareRelation rel)
	{
		return leftOperand.compare(rightOperand, rel)
	}

	override walkParameterRead(ParameterReadExpression expression)
	{
		val param = expression.param as EnumParameter
		val paramText = expression.scope.getName() + ":" + param.name
		if (lrValueSwitch == LRValueSwitch.RValue)
		{
			rValue = new GlobalVariable(paramText)
		}
		else
		{
			lValue = new StorageObject(paramText, true)

			// Add possible values for generating tests purpose
			val possibleValues = new ArrayList<String>
			for (literal : param.literals)
			{
				possibleValues.add(literal.name)
			}
			factory.addPossibleValues(paramText, possibleValues)
		}
	}

	override walkEnumLiteralReference(EnumLiteral literal)
	{
		rValue = new GlobalVariable((literal.eContainer as EnumParameter).name + "." + literal.name)
	}

	override walkLocalVariableReference(LocalVariableDeclaration declaration)
	{
		if (lrValueSwitch == LRValueSwitch.RValue)
		{
			rValue = localVarHandler.getVariable(declaration.name)
		}
		else
		{
			lValue = new StorageObject(declaration.name, false)
		}
	}

	override walkNumberLiteral(NumberLiteral literal)
	{
		rValue = new Constant(literal.value)
	}

	override walkParameterReference(ParameterDeclaration declaration)
	{
		if (lrValueSwitch == LRValueSwitch.RValue)
		{
			rValue = new GlobalVariable(declaration.name)
		}
		else
		{
			lValue = new StorageObject(declaration.name, true)
		}
	}

	def HLTBoolExpression getCurBoolExpr()
	{
		return (rValue as LocalVariable).getConditionForValue("true")
	}

	def void setCurBoolExpr(HLTBoolExpression expression)
	{
		val localVar = new LocalVariable("true", expression)
		rValue = localVar
	}

	override getLog()
	{
		factory.generate
	}

}

class HLTLocalVariables
{
	Map<String, LocalVariable> localVars = new HashMap

	def declare(String varName)
	{
		localVars.put(varName, new LocalVariable)
	}

	def getVariable(String varName)
	{
		return localVars.get(varName)
	}

	def isDefined(String varName)
	{
		return localVars.containsKey(varName)
	}
}
