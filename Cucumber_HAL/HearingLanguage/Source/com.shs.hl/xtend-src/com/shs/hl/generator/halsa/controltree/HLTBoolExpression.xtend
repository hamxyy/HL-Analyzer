package com.shs.hl.generator.halsa.controltree

import com.shs.hl.generator.halsa.evaluation.EvaluationContext

interface HLTBoolExpression
{
	def boolean execute(EvaluationContext context)

	def HLTBoolExpression negate()
}

class HLTSingleValue implements HLTBoolExpression
{
	public boolean value

	new(boolean value)
	{
		this.value = value
	}

	override execute(EvaluationContext context)
	{
		return value
	}

	override negate()
	{
		return new HLTSingleValue(!value)
	}

	override toString()
	{
		return value.toString
	}

}

class HLTLogicalExpression implements HLTBoolExpression
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

	override execute(EvaluationContext context)
	{
		switch (rel)
		{
			case And:
				return left.execute(context) && left.execute(context)
			case Or:
				return left.execute(context) || left.execute(context)
			default:
				throw new Exception()
		}
	}

	override negate()
	{
		return new HLTLogicalExpression(left.negate, rel.negate, right.negate)
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

class HLTCompareExpression implements HLTBoolExpression
{
	Object left
	Object right
	HLTCompareRelation rel

	new(Object left, HLTCompareRelation rel, Object right)
	{
		this.left = left
		this.right = right
		this.rel = rel
	}

	override execute(EvaluationContext context)
	{
		switch (rel)
		{
			case Equals:
				return left.equals(right)
			case Unequals:
				return !left.equals(right)
			case GreaterThan:
				return Integer.compare(left as Integer, right as Integer) > 0
			case LessThan:
				return Integer.compare(left as Integer, right as Integer) < 0
			case GreaterThanOrEquals:
				return Integer.compare(left as Integer, right as Integer) >= 0
			case LessThanOrEquals:
				return Integer.compare(left as Integer, right as Integer) <= 0
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

	override negate()
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
