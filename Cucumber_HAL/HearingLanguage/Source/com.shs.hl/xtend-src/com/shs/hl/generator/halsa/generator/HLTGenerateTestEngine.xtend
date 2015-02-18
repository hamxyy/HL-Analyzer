package com.shs.hl.generator.halsa.generator

import com.shs.common.commonLanguage.EnumLiteral
import com.shs.common.commonLanguage.EnumParameter
import com.shs.hl.generator.halsa.HalsaASTWalkerBase
import com.shs.hl.generator.halsa.controltree.HLTBoolExpression
import com.shs.hl.generator.halsa.controltree.HLTCompareExpression
import com.shs.hl.generator.halsa.controltree.HLTCompareRelation
import com.shs.hl.generator.halsa.controltree.HLTConditionNode
import com.shs.hl.generator.halsa.controltree.HLTControlTreeNode
import com.shs.hl.generator.halsa.controltree.HLTExecutionNode
import com.shs.hl.generator.halsa.controltree.HLTLogicalExpression
import com.shs.hl.generator.halsa.controltree.HLTLogicalRelation
import com.shs.hl.generator.halsa.evaluation.LRValueSwitch
import com.shs.hl.hearingLanguage.And
import com.shs.hl.hearingLanguage.AssignmentStatement
import com.shs.hl.hearingLanguage.Equals
import com.shs.hl.hearingLanguage.FunctionDeclaration
import com.shs.hl.hearingLanguage.Greater
import com.shs.hl.hearingLanguage.GreaterOrEquals
import com.shs.hl.hearingLanguage.IfStatement
import com.shs.hl.hearingLanguage.Namespace
import com.shs.hl.hearingLanguage.NumberLiteral
import com.shs.hl.hearingLanguage.Or
import com.shs.hl.hearingLanguage.ParameterDeclaration
import com.shs.hl.hearingLanguage.ParameterReadExpression
import com.shs.hl.hearingLanguage.Smaller
import com.shs.hl.hearingLanguage.SmallerOrEquals
import com.shs.hl.hearingLanguage.UnEquals
import com.shs.hl.generator.halsa.testcase.HLTTestSuiteFactory
import com.shs.hl.generator.halsa.controltree.HLTControlFlowTree

class HLTGenerateTestEngine extends HalsaASTWalkerBase
{
	HLTControlTreeNode curNode
	HLTBoolExpression curBoolExpr
	String lValue
	String rValue

	HLTTestSuiteFactory factory

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

		factory.forFunction(declaration.name)
		factory.forControlTree(controlTree)
	}

	override walkIfStatement(IfStatement ifStmt)
	{
		super.walkIfCondition(ifStmt.condition)
		val ifCondition = curBoolExpr
		var node = new HLTConditionNode(curBoolExpr)
		curNode.append(node)
		curNode = node
		super.walkStatementList(ifStmt.thenBody)
		curNode = curNode.parent
		
		for (elseIf : ifStmt.elseIfs)
		{
			super.walkIfCondition(elseIf.condition)
			val elseIfCondition = new HLTLogicalExpression(ifCondition.negate, HLTLogicalRelation.And, curBoolExpr)
			node = new HLTConditionNode(elseIfCondition)
			curNode.append(node)
			curNode = node
			super.walkStatementList(elseIf.body)
			curNode = curNode.parent
		}

		if (ifStmt.elseBody != null)
		{
			val elseCondition = ifCondition.negate
			node = new HLTConditionNode(elseCondition)
			curNode.append(node)
			curNode = node
			super.walkStatementList(ifStmt.elseBody)
			curNode = curNode.parent
		}
	}

	override walkAssignmentStatement(AssignmentStatement assign)
	{
		lrValueSwitch = LRValueSwitch.LValue
		walkExpression(assign.lvalue)
		lrValueSwitch = LRValueSwitch.RValue
		walkExpression(assign.rvalue)
		curNode.append(new HLTExecutionNode(lValue, rValue))
	}

	override walkAndExpression(And andExpr)
	{
		super.walkExpression(andExpr.left)
		val left = curBoolExpr
		super.walkExpression(andExpr.right)
		val right = curBoolExpr
		curBoolExpr = new HLTLogicalExpression(left, HLTLogicalRelation.And, right)
	}

	override walkOrExpression(Or orExpr)
	{
		super.walkExpression(orExpr.left)
		val left = curBoolExpr
		super.walkExpression(orExpr.right)
		val right = curBoolExpr
		curBoolExpr = new HLTLogicalExpression(left, HLTLogicalRelation.Or, right)
	}

	override walkEqualsExpression(Equals equals)
	{
		lrValueSwitch = LRValueSwitch.LValue
		walkExpression(equals.left)
		lrValueSwitch = LRValueSwitch.RValue
		walkExpression(equals.right)
		curBoolExpr = new HLTCompareExpression(lValue, HLTCompareRelation.Equals, rValue)
	}

	override walkUnEqualsExpression(UnEquals unEquals)
	{
		lrValueSwitch = LRValueSwitch.LValue
		walkExpression(unEquals.left)
		lrValueSwitch = LRValueSwitch.RValue
		walkExpression(unEquals.right)
		curBoolExpr = new HLTCompareExpression(lValue, HLTCompareRelation.Unequals, rValue)
	}

	override walkGreaterExpression(Greater greater)
	{
		lrValueSwitch = LRValueSwitch.LValue
		walkExpression(greater.left)
		lrValueSwitch = LRValueSwitch.RValue
		walkExpression(greater.right)
		curBoolExpr = new HLTCompareExpression(lValue, HLTCompareRelation.GreaterThan, rValue)
	}

	override walkSmallerExpression(Smaller smaller)
	{
		lrValueSwitch = LRValueSwitch.LValue
		walkExpression(smaller.left)
		lrValueSwitch = LRValueSwitch.RValue
		walkExpression(smaller.right)
		curBoolExpr = new HLTCompareExpression(lValue, HLTCompareRelation.LessThan, rValue)
	}

	override walkGreaterOrEqualsExpression(GreaterOrEquals greaterOrEquals)
	{
		lrValueSwitch = LRValueSwitch.LValue
		walkExpression(greaterOrEquals.left)
		lrValueSwitch = LRValueSwitch.RValue
		walkExpression(greaterOrEquals.right)
		curBoolExpr = new HLTCompareExpression(lValue, HLTCompareRelation.GreaterThanOrEquals, rValue)
	}

	override walkSmallerOrEqualsExpression(SmallerOrEquals smallerOrEquals)
	{
		lrValueSwitch = LRValueSwitch.LValue
		walkExpression(smallerOrEquals.left)
		lrValueSwitch = LRValueSwitch.RValue
		walkExpression(smallerOrEquals.right)
		curBoolExpr = new HLTCompareExpression(lValue, HLTCompareRelation.LessThanOrEquals, rValue)
	}

	//	override walkFunctionCall(FunctionDeclaration function, ArgList argList)
	//	{
	//		super.walkFunctionCall(function, argList)
	//	}
	override walkParameterRead(ParameterReadExpression expression)
	{
		if (lrValueSwitch == LRValueSwitch.RValue)
		{
			val param = expression.param as EnumParameter

		//			activeValues.push(context.getGlobal(expression.scope.getName() + ":" + param.name))
		}
		else
		{
			val param = expression.param as EnumParameter
			lValue = expression.scope.getName() + ":" + param.name
		}
	}

	override walkEnumLiteralReference(EnumLiteral literal)
	{
		rValue = literal.name
	}

	override walkNumberLiteral(NumberLiteral literal)
	{
		rValue = literal.value
	}

	override walkParameterReference(ParameterDeclaration declaration)
	{
		if (lrValueSwitch == LRValueSwitch.RValue)
		{
			//			activeValues.push(context.getGlobal(expression.scope.getName() + ":" + param.name))
		}
		else
		{
			lValue = declaration.name
		}
	}

	override getLog()
	{
		factory.generate
	}

}
