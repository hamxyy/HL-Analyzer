package com.shs.hl.generator.halsa

import com.shs.hl.hearingLanguage.And
import com.shs.hl.hearingLanguage.Expression
import com.shs.hl.hearingLanguage.Or

interface IExpressionHandler
{

	def void enterCondition(Expression expression)

	def void exitCondition(Expression expression)

	def void handle(And and)

	def void handle(Or or)

}

class IfHandler implements IExpressionHandler
{
	boolean isInCondition

	override enterCondition(Expression expression)
	{
		isInCondition = true
	}

	override exitCondition(Expression expression)
	{
		isInCondition = false
	}

	override handle(And and)
	{
	}

	override handle(Or or)
	{
	}

}

class DummyHandler implements IExpressionHandler
{

	override enterCondition(Expression expression)
	{
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}

	override exitCondition(Expression expression)
	{
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}

	override handle(And and)
	{
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}

	override handle(Or or)
	{
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}

}
