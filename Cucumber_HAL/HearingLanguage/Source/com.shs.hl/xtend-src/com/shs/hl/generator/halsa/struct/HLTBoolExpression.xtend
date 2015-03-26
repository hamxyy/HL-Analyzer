package com.shs.hl.generator.halsa.struct

import java.util.ArrayList
import java.util.List
import java.util.Map

interface HLTBoolExpression
{
	def boolean evaluate()

	def boolean evaluate(Map<String, String> variables)

	def HLTBoolExpression not()

	def HLTBoolExpression and(HLTBoolExpression expr)

	def HLTBoolExpression or(HLTBoolExpression expr)

	def boolean equivalent(HLTBoolExpression expr)

	def boolean imply(HLTBoolExpression expr)

	def List<String> getAllOperands()
}

abstract class HLTBoolExpressionBase implements HLTBoolExpression
{
	override and(HLTBoolExpression expr)
	{
		if (this.imply(expr))
		{
			return this
		}
		else if (expr.imply(this))
		{
			return expr
		}
		else if (expr instanceof HLTConstantExpression)
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
		if (this.imply(expr))
		{
			return expr
		}
		else if (expr.imply(this))
		{
			return this
		}
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

	override evaluate(Map<String, String> variables)
	{
		switch (rel)
		{
			case And:
				return left.evaluate(variables) && left.evaluate(variables)
			case Or:
				return left.evaluate(variables) || left.evaluate(variables)
			default:
				throw new Exception()
		}
	}

	override not()
	{
		return new HLTLogicalExpression(left.not, rel.not, right.not)
	}

	private def not(HLTLogicalRelation relation)
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
		return "(" + left + " " + rel + " " + right + ")"
	}

	override getAllOperands()
	{
		val allOperandsLeft = left.allOperands
		allOperandsLeft.addAll(right.allOperands)
		return allOperandsLeft
	}

	override equivalent(HLTBoolExpression expr)
	{
		if (!(expr instanceof HLTLogicalExpression))
			return false
		val that = expr as HLTLogicalExpression

		// OR previous flag for short circuit evaluation, meaning if e1 is true then no need to evaluate the rest.
		val e0 = false
		val e1 = e0 || (that.rel.equals(rel) && (that.left.equals(left) && that.right.equals(right)))
		val e2 = e1 || (that.rel.equals(rel) && (that.right.equals(left) && that.left.equals(right)))
		val e3 = e2 || (that.rel.equals(rel.not) && (that.left.equals(left.not) && that.right.equals(right.not)))
		val e4 = e3 || (that.rel.equals(rel.not) && (that.right.equals(left.not) && that.left.equals(right.not)))
		return e4
	}

	override imply(HLTBoolExpression expr)
	{
		if (this.equivalent(expr))
		{
			return true
		}

		switch (rel)
		{
			case And:
				return left.imply(expr) || right.imply(expr)
			case Or:
				return left.imply(expr) && right.imply(expr)
			default:
				throw new Exception()
		}
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
		return evaluate(left, right)
	}

	override evaluate(Map<String, String> variables)
	{
		var left = this.left
		var right = this.right
		if (variables.containsKey(left))
		{
			left = variables.get(left)
		}
		if (variables.containsKey(right))
		{
			right = variables.get(right)
		}
		return evaluate(left, right)
	}

	private def evaluate(String left, String right)
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
		return new HLTCompareExpression(left, rel.not, right)
	}

	private def not(HLTCompareRelation relation)
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

	private def exchange(HLTCompareRelation relation)
	{
		switch (rel)
		{
			case Equals:
				return HLTCompareRelation.Equals
			case Unequals:
				return HLTCompareRelation.Unequals
			case GreaterThan:
				return HLTCompareRelation.LessThan
			case LessThan:
				return HLTCompareRelation.GreaterThan
			case GreaterThanOrEquals:
				return HLTCompareRelation.LessThanOrEquals
			case LessThanOrEquals:
				return HLTCompareRelation.GreaterThanOrEquals
			default:
				throw new Exception()
		}
	}

	override toString()
	{
		return "(" + left + " " + rel + " " + right + ")"
	}

	override getAllOperands()
	{
		val list = new ArrayList
		list.add(left)
		list.add(right)
		return list
	}

	override equivalent(HLTBoolExpression obj)
	{
		if (!(obj instanceof HLTCompareExpression))
			return false
		val that = obj as HLTCompareExpression

		// left op right || right op.exchange left. E.g. a>b == b<a
		return ( that.rel.equals(rel) && that.left.equals(left) && that.right.equals(right) ) ||
			(that.rel.equals(rel.exchange) && that.right.equals(left) && that.left.equals(right))
	}

	override imply(HLTBoolExpression expr)
	{
		if (this.equivalent(expr))
		{
			return true
		}
		else if (expr instanceof HLTLogicalExpression)
		{
			val logicalExpr = expr as HLTLogicalExpression
			switch (logicalExpr.relation)
			{
				case And:
				{
					this.imply(logicalExpr.left) && this.imply(logicalExpr.right)
				}
				case Or:
				{
					this.imply(logicalExpr.left) || this.imply(logicalExpr.right)
				}
				default:
					throw new Exception
			}
		}
		else
		{
			return false
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

	override evaluate(Map<String, String> variables)
	{
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}

	override not()
	{
		throw new UnsupportedOperationException("HLTDummyBoolExpression.not is not supposed to be called.")
	}

	override getAllOperands()
	{
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}

	override equivalent(HLTBoolExpression obj)
	{
		return false
	}

	override imply(HLTBoolExpression expr)
	{
		return false
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

	override evaluate(Map<String, String> variables)
	{
		return evaluate()
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

	override getAllOperands()
	{
		return new ArrayList
	}

	override equivalent(HLTBoolExpression expr)
	{
		if (!(expr instanceof HLTConstantExpression))
			return false
		return (expr as HLTConstantExpression).value == value
	}

	override imply(HLTBoolExpression expr)
	{
		return false
	}

}

//
//class HLTEvaluableExpression extends HLTBoolExpressionBase
//{
//	LocalVariable left
//	HLTCompareRelation relation
//	ExpressionResult right
//
//	new(LocalVariable left, HLTCompareRelation relation, ExpressionResult right)
//	{
//		this.left = left
//		this.relation = relation
//		this.right = right
//	}
//
//	override evaluate()
//	{
//		throw new UnsupportedOperationException("TODO: auto-generated method stub")
//	}
//
//	override evaluate(Map<String, String> variables)
//	{
//		throw new UnsupportedOperationException("TODO: auto-generated method stub")
//	}
//
//	override not()
//	{
//		throw new UnsupportedOperationException("TODO: auto-generated method stub")
//	}
//
//	override getAllOperands()
//	{
//		
//	}
//
//}
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
