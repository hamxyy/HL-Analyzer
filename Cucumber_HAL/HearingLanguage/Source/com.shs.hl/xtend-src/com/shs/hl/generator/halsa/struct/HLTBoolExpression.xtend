package com.shs.hl.generator.halsa.struct

interface HLTBoolExpression
{
	def boolean evaluate()

	def HLTBoolExpression not()

	def HLTBoolExpression and(HLTBoolExpression expr)

	def HLTBoolExpression or(HLTBoolExpression expr)
}

abstract class HLTBoolExpressionBase implements HLTBoolExpression
{

	override and(HLTBoolExpression expr)
	{
		if (expr instanceof HLTConstantExpression)
		{
			return expr.and(this)
		}
		else
		{
			return new HLTLogicalExpression(this, HLTLogicalRelation.And, expr)
		}
	}

	override or(HLTBoolExpression expr)
	{
		if (expr instanceof HLTConstantExpression)
		{
			return expr.or(this)
		}
		else
		{
			return new HLTLogicalExpression(this, HLTLogicalRelation.Or, expr)
		}
	}

}

class HLTDummyBoolExpression extends HLTBoolExpressionBase
{
	override and(HLTBoolExpression expr)
	{
		return expr
	}

	override or(HLTBoolExpression expr)
	{
		return expr
	}

	override evaluate()
	{
		throw new UnsupportedOperationException("HLTDummyBoolExpression.evaluate is not supposed to be called.")
	}

	override not()
	{
		throw new UnsupportedOperationException("HLTDummyBoolExpression.not is not supposed to be called.")
	}

}

class HLTConstantExpression extends HLTBoolExpressionBase
{
	def static HLTConstantExpression alwaysTrue()
	{
		return new HLTConstantExpression(true)
	}

	def static HLTConstantExpression alwaysFalse()
	{
		return new HLTConstantExpression(false)
	}

	def static HLTConstantExpression evaluate(String left, HLTCompareRelation rel, String right)
	{
		return new HLTConstantExpression(new HLTCompareExpression(left, rel, right).evaluate())
	}

	boolean value

	private new(boolean value)
	{
		this.value = value
	}

	override evaluate()
	{
		return value
	}

	override not()
	{
		return new HLTConstantExpression(!value)
	}

	override and(HLTBoolExpression expr)
	{
		if (value == true)
			expr
		else
			return this
	}

	override or(HLTBoolExpression expr)
	{
		if (value == true)
			this
		else
			return expr
	}

	override toString()
	{
		return value.toString
	}

}

class HLTLogicalExpression extends HLTBoolExpressionBase
{
	HLTBoolExpression left
	HLTBoolExpression right
	HLTLogicalRelation rel

	new(HLTBoolExpression left, HLTLogicalRelation rel, HLTBoolExpression right)
	{
		this.left = left
		this.right = right
		this.rel = rel
	}

	override evaluate()
	{
		switch (rel)
		{
			case And:
				return left.evaluate() && left.evaluate()
			case Or:
				return left.evaluate() || left.evaluate()
			default:
				throw new Exception()
		}
	}

	override not()
	{
		return new HLTLogicalExpression(left.not, rel.negate, right.not)
	}

	def negate(HLTLogicalRelation relation)
	{
		return if (relation == HLTLogicalRelation.And) HLTLogicalRelation.Or else HLTLogicalRelation.And;
	}

	def getLeft()
	{
		return left
	}

	def getRight()
	{
		return right
	}

	def getRelation()
	{
		return rel
	}

	override toString()
	{
		return left + " " + rel + " " + right
	}
}

class HLTCompareExpression extends HLTBoolExpressionBase
{
	String left
	String right
	HLTCompareRelation rel

	new(String left, HLTCompareRelation rel, String right)
	{
		this.left = left
		this.right = right
		this.rel = rel
	}

	override evaluate()
	{
		switch (rel)
		{
			case Equals:
				return left.equals(right)
			case Unequals:
				return !left.equals(right)
			case GreaterThan:
				return Integer.compare(Integer.parseInt(left), Integer.parseInt(right)) > 0
			case LessThan:
				return Integer.compare(Integer.parseInt(left), Integer.parseInt(right)) < 0
			case GreaterThanOrEquals:
				return Integer.compare(Integer.parseInt(left), Integer.parseInt(right)) >= 0
			case LessThanOrEquals:
				return Integer.compare(Integer.parseInt(left), Integer.parseInt(right)) <= 0
			default:
				throw new Exception()
		}
	}

	def getLeft()
	{
		return left
	}

	def getRight()
	{
		return right
	}

	def getRelation()
	{
		return rel
	}

	override not()
	{
		return new HLTCompareExpression(left, rel.negate, right)
	}

	def negate(HLTCompareRelation relation)
	{
		switch (rel)
		{
			case Equals:
				return HLTCompareRelation.Unequals
			case Unequals:
				return HLTCompareRelation.Equals
			case GreaterThan:
				return HLTCompareRelation.LessThanOrEquals
			case LessThan:
				return HLTCompareRelation.GreaterThanOrEquals
			case GreaterThanOrEquals:
				return HLTCompareRelation.LessThan
			case LessThanOrEquals:
				return HLTCompareRelation.GreaterThan
			default:
				throw new Exception()
		}
	}

	override toString()
	{
		return left + " " + rel + " " + right
	}
}

enum HLTLogicalRelation
{
	And,
	Or
}

enum HLTCompareRelation
{
	Equals,
	Unequals,
	GreaterThan,
	LessThan,
	GreaterThanOrEquals,
	LessThanOrEquals

}
