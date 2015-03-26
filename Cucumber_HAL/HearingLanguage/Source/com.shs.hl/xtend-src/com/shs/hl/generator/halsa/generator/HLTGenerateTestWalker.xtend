package com.shs.hl.generator.halsa.generator

import com.shs.common.commonLanguage.EnumLiteral
import com.shs.common.commonLanguage.EnumParameter
import com.shs.hl.generator.halsa.HLTSyntaxTreeWalkerBase
import com.shs.hl.generator.halsa.evaluation.LRValueSwitch
import com.shs.hl.generator.halsa.service.SymbolDeclarationService
import com.shs.hl.generator.halsa.struct.ConditionalValue
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
import com.shs.hl.generator.halsa.struct.HLTReturnNode
import com.shs.hl.generator.halsa.struct.LocalVariable
import com.shs.hl.generator.halsa.struct.StorageObject
import com.shs.hl.generator.halsa.struct.TempExpressionResult
import com.shs.hl.hearingLanguage.And
import com.shs.hl.hearingLanguage.ArgList
import com.shs.hl.hearingLanguage.AssignmentStatement
import com.shs.hl.hearingLanguage.Equals
import com.shs.hl.hearingLanguage.Expression
import com.shs.hl.hearingLanguage.FalseLiteral
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
import com.shs.hl.hearingLanguage.PlusEqualsStatement
import com.shs.hl.hearingLanguage.PreIncrementExpr
import com.shs.hl.hearingLanguage.ReturnStatement
import com.shs.hl.hearingLanguage.Smaller
import com.shs.hl.hearingLanguage.SmallerOrEquals
import com.shs.hl.hearingLanguage.StatementList
import com.shs.hl.hearingLanguage.SwitchStatement
import com.shs.hl.hearingLanguage.TrueLiteral
import com.shs.hl.hearingLanguage.UnEquals
import com.shs.hl.hearingLanguage.WhileStatement
import java.util.ArrayList
import java.util.HashMap
import java.util.List
import java.util.Map
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.generator.IFileSystemAccess

class HLTGenerateTestWalker extends HLTSyntaxTreeWalkerBase
{
	String packageName
	val String RETURN_VALUE_KEY = "$return"

	HLTControlFlowTreeNode curNode
	HLTLocalVariables localVarHandler
	StorageObject lValue
	ExpressionResult rValue

	HLTTestSuiteFactory factory = new HLTTestSuiteFactory

	boolean isSubFunction

	def generate(Resource resource, IFileSystemAccess fsa, String packageName)
	{
		this.packageName = packageName
		super.walk(resource, fsa)
	}

	override walkApplicationMacro(Namespace macro)
	{
		factory.forMacro(macro.name)
		super.walkApplicationMacro(macro)
	}

	override walkLibrary(Namespace macro)
	{
		factory.forMacro(macro.name)
		super.walkLibrary(macro)
	}

	override walkFunction(FunctionDeclaration declaration)
	{
		isSubFunction = false
		localVarHandler = new HLTLocalVariables
		localVarHandler.declare(RETURN_VALUE_KEY)
		var controlTree = generateControlFlowTree(declaration)

//		val s = controlTree.format
		val args = new ArrayList<String>
		declaration.args.forEach[a|args.add(a.name)]
		factory.forFunction(declaration.name, args, controlTree)
	}

	private def HLTControlFlowTree generateControlFlowTree(FunctionDeclaration declaration)
	{
		var controlTree = HLTControlFlowTree.create
		curNode = controlTree.root
		super.walkFunction(declaration)
		return controlTree
	}

	override walkFunctionCall(FunctionDeclaration function, ArgList argList)
	{
		val funcWalker = new HLTGenerateTestWalker
		funcWalker.localVarHandler = new HLTLocalVariables
		funcWalker.localVarHandler.declare(RETURN_VALUE_KEY)
		funcWalker.isSubFunction = true
		for (var i = 0; i < function.args.length; i++)
		{
			val argName = function.args.get(i).name
			walkExpression(argList.args.get(i))
			funcWalker.localVarHandler.define(argName, rValue)
		}

		var controlTree = funcWalker.generateControlFlowTree(function)
		rValue = funcWalker.returnValue // Get the return value.
		for (node : controlTree.root.children)
		{
			curNode.append(node)
		}
	}

	def getReturnValue()
	{
		return localVarHandler.getVariable(RETURN_VALUE_KEY)
	}

	override walkReturnStatement(ReturnStatement returnStmt)
	{
		lrValueSwitch = LRValueSwitch.RValue
		super.walkReturnStatement(returnStmt)
		returnValue.assign(rValue)

		if (!isSubFunction)
		{
			for (value : rValue.allValues)
			{
				val condNode = new HLTConditionNode(value.condition)
				condNode.append(new HLTReturnNode(value.value))
				curNode.append(condNode)
			}
		}
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
				walkBlock(ifStmt.thenBody)
			}
			else
			{
				//throw new Exception("Unreachable statements!")
			}
		}
		else
		{
			var ifConditionNode = new HLTConditionNode(curBoolExpr)
			curNode.append(ifConditionNode)
			curNode = ifConditionNode
			walkBlock(ifStmt.thenBody)
			curNode = curNode.parent
		}

		for (elseIf : ifStmt.elseIfs)
		{
			super.walkIfCondition(elseIf.condition)
			val elseIfCondition = ifCondition.not.and(curBoolExpr)
			var elseIfConditionNdoe = new HLTConditionNode(elseIfCondition)
			curNode.append(elseIfConditionNdoe)
			curNode = elseIfConditionNdoe
			walkBlock(elseIf.body)
			curNode = curNode.parent
		}

		if (ifStmt.elseBody != null)
		{
			curBoolExpr = ifCondition.not
			var elseConditionNode = new HLTConditionNode(curBoolExpr)
			curNode.append(elseConditionNode)
			curNode = elseConditionNode
			walkBlock(ifStmt.elseBody)
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
			curBoolExpr = caseCondition
			walkBlock(eachCase.body)
			curNode = curNode.parent
		}

		if (switchStmt.defaultBlock != null)
		{
			var defaultConditionNode = new HLTConditionNode(defaultCondition)
			curNode.append(defaultConditionNode)
			curNode = defaultConditionNode
			curBoolExpr = defaultCondition
			walkBlock(switchStmt.defaultBlock)
			curNode = curNode.parent
		}
	}

	override walkWhileStatement(WhileStatement statement)
	{
		localVarHandler.enterNewScope(HLTConstantExpression.alwaysTrue)
		var loop = true
		while (loop)
		{
			this.lrValueSwitch = LRValueSwitch.RValue
			walkExpression(statement.expr as Expression)
			val condition = curBoolExpr
			if (condition instanceof HLTConstantExpression)
			{
				if (condition.evaluate)
				{
					super.walkStatementList(statement.body)
				}
				else
				{
					curNode = curNode.parent
					loop = false // Otherwise exit the loop
				}
			}
			else
			{
				val condNode = new HLTConditionNode(condition)
				curNode.append(condNode)
				curNode = condNode
				super.walkStatementList(statement.body)

				loop = true
			}
		}
		localVarHandler.exitScope()

	}

	private def walkBlock(StatementList statements)
	{
		localVarHandler.enterNewScope(curBoolExpr)
		super.walkStatementList(statements)
		localVarHandler.exitScope()
	}

	private def List<Map<String, ConditionalValue>> makeAllCombinations(List<String> variables)
	{
		val variablesSize = variables.length

		var combinations = new ArrayList<Map<String, ConditionalValue>>(variablesSize)
		if (variablesSize == 0) return combinations
		var Map<String, ConditionalValue> combination
		val total = new ArrayList<Integer>(variablesSize)
		val counter = new ArrayList<Integer>(variablesSize)
		for (variable : variables)
		{
			if (localVarHandler.isDefined(variable))
			{
				total.add(localVarHandler.getVariable(variable).allValues.length)
			}
			else
			{
				total.add(1)
			}
			counter.add(1)
		}
		var digit = 0
		var ok = false
		while (!ok)
		{
			combination = new HashMap

			for (var i = 0; i < variablesSize; i++)
			{
				val varName = variables.get(i)
				if (localVarHandler.isDefined(varName))
				{
					combination.put(varName, localVarHandler.getVariable(varName).allValues.get(counter.get(i) - 1))
				}
				else
				{
					combination.put(varName, new ConditionalValue(varName, HLTConstantExpression.alwaysTrue))
				}
			}
			combinations.add(combination)

			ok = true
			for (var x = 0; x < variablesSize; x++)
			{
				if (counter.get(x) != total.get(x))
				{
					ok = false
				}
			}

			counter.set(digit, counter.get(digit) + 1)
			for (var x = 0; x < variablesSize - 1; x++)
			{
				if (counter.get(x) > total.get(x))
				{
					counter.set(x, 1)
					counter.set(x + 1, counter.get(x + 1) + 1)
					digit = 1
				}
			}
		}

		return combinations
	}

	override walkLocalVariableDeclarationStatement(LocalVariableDeclarationStatement statement)
	{
		var localVarDeclaration = statement.^var as LocalVariableDeclaration

		walkExpression(localVarDeclaration.init as Expression)
		localVarHandler.define(localVarDeclaration.name, rValue)
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
			oldVar.assign(newValue)
		}
	}

	override walkPlusEqualsStatement(PlusEqualsStatement assign)
	{
		lrValueSwitch = LRValueSwitch.LValue
		walkExpression(assign.lvalue)
		lrValueSwitch = LRValueSwitch.RValue
		walkExpression(assign.rvalue)

		if (lValue.isGlobal)
		{
			//			var HLTExecutionNode node = new HLTExecutionNode(lValue.name, (rValue as GlobalVariable).name)
			//			curNode.append(node)
		}
		else
		{
			val increment = (rValue as Constant).value
			val oldVar = localVarHandler.getVariable(lValue.name)
			oldVar.plusEquals(increment)
		}
	}

	override walkPreIncrementExpr(PreIncrementExpr expr)
	{
		lrValueSwitch = LRValueSwitch.LValue
		walkExpression(expr.expr)

		val increment = "1"
		val oldVar = localVarHandler.getVariable(lValue.name)
		oldVar.plusEquals(increment)
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
		val struct = SymbolDeclarationService.getStruct(expression.param)
		val paramText = expression.scope.getName() + ":" + struct.name
		if (lrValueSwitch == LRValueSwitch.RValue)
		{
			rValue = new GlobalVariable(paramText)
		}
		else
		{
			lValue = new StorageObject(paramText, true)
		}
		factory.addPossibleValues(paramText, struct.possibleValues)
	}

	override walkParameterReference(ParameterDeclaration declaration)
	{
		if (lrValueSwitch == LRValueSwitch.RValue)
		{
			if (localVarHandler.isDefined(declaration.name))
			{
				rValue = localVarHandler.getVariable(declaration.name)
			}
			else
			{
				rValue = new GlobalVariable(declaration.name)
			}
		}
		else
		{
			lValue = new StorageObject(declaration.name, true)
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

	override walkFalseLiteral(FalseLiteral literal)
	{
		rValue = new Constant("false")
	}

	override walkTrueLiteral(TrueLiteral literal)
	{
		rValue = new Constant("true")
	}

	def HLTBoolExpression getCurBoolExpr()
	{
		return (rValue as TempExpressionResult<HLTBoolExpression>).value
	}

	def void setCurBoolExpr(HLTBoolExpression expression)
	{
		rValue = new TempExpressionResult<HLTBoolExpression>(expression)
	}

	override generateFile(IFileSystemAccess fsa, String macroName, String error)
	{
		fsa.generateFile(
			"/generated/" + packageName + "/test_" + macroName + ".hlt",
			error + "\n" + factory.generate
		)
	}

}

class HLTLocalVariables
{
	Map<String, LocalVariable> localVars = new HashMap

	def declare(String varName)
	{
		addVariable(varName, new LocalVariable())
	}

	def define(String varName, ExpressionResult value)
	{
		val localVariable = new LocalVariable()
		localVariable.assign(value)
		return addVariable(varName, localVariable)
	}

	def enterNewScope(HLTBoolExpression condition)
	{
		for (variable : localVars.values)
		{
			variable.enterNewScope(condition)
		}
	}

	def exitScope()
	{
		for (variable : localVars.values)
		{
			variable.exitScope()
		}
	}

	private def addVariable(String varName, LocalVariable localVariable)
	{
		localVars.put(varName, localVariable)
		return localVariable
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
