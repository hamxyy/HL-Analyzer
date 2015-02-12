package com.shs.hl.generator.halsa

import com.shs.common.commonLanguage.EnumLiteral
import com.shs.common.commonLanguage.EnumParameter
import com.shs.hl.hearingLanguage.And
import com.shs.hl.hearingLanguage.ArgList
import com.shs.hl.hearingLanguage.Expression
import com.shs.hl.hearingLanguage.FunctionDeclaration
import com.shs.hl.hearingLanguage.IfStatement
import com.shs.hl.hearingLanguage.Namespace
import com.shs.hl.hearingLanguage.Or
import com.shs.hl.hearingLanguage.ParameterReadExpression

class GenerateTestASTWalker extends TemplateHalsaASTWalker
{
	IExpressionHandler curExprHandler
	IExpressionHandler ifExprHandler = new IfHandler
	IExpressionHandler dummyExprHandler = new DummyHandler
	MacroBoundaryValuesCollection boundaryValues = new MacroBoundaryValuesCollection
	boolean withinFunctionCall = false
	String currentFunction
	String scope
	String id

	override void walkLibrary(Namespace namespace)
	{}

	override void walkApplicationMacro(Namespace namespace)
	{}

	override walkIfStatement(IfStatement ifStmt)
	{
		curExprHandler = ifExprHandler
		super.walkIfStatement(ifStmt)
		curExprHandler = dummyExprHandler
	}

	override walkIfCondition(Expression condition)
	{
		curExprHandler.enterCondition(condition)
		super.walkIfCondition(condition)
		curExprHandler.exitCondition(condition)
	}

	override walkAndExpression(And andExpr)
	{
		curExprHandler.handle(andExpr)
		super.walkAndExpression(andExpr)
	}

	override walkOrExpression(Or orExpr)
	{
		curExprHandler.handle(orExpr)
		super.walkOrExpression(orExpr)
	}

	override walkFunction(FunctionDeclaration declaration)
	{
		if (!withinFunctionCall)
		{
			boundaryValues.forFunction(declaration.name)
		}

		currentFunction = (declaration.eContainer as Namespace).name + "." + declaration.name
	}

	override walkFunctionCall(FunctionDeclaration function, ArgList argList)
	{
		withinFunctionCall = true
		super.walkFunctionCall(function, argList)
		withinFunctionCall = false
	}

	override walkParameterRead(ParameterReadExpression paramReadExpr)
	{
		scope = paramReadExpr.scope.getName()
		id = paramReadExpr.param.name
	}

	override walkEnumLiteralReference(EnumLiteral literal)
	{
		val enumParam = literal.eContainer as EnumParameter
		boundaryValues.current.putWithLocation(enumParam.scope.getName(), enumParam.name, literal.name, currentFunction)
	}

	override getLog()
	{
		boundaryValues.forPrint
	}

}
