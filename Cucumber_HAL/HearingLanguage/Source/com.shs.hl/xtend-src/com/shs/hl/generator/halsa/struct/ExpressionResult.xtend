package com.shs.hl.generator.halsa.struct

import java.util.ArrayList
import java.util.HashMap
import java.util.List
import java.util.Map

interface ExpressionResult
{
	def List<ConditionalValue> getAllValues()

	def HLTBoolExpression compare(ExpressionResult that, HLTCompareRelation relation)
}

class LocalVariable implements ExpressionResult
{
	Map<String, HLTBoolExpression> values = new HashMap

	new()
	{
	}

	new(String value, HLTBoolExpression expression)
	{
		this.put(value, expression)
	}

	def put(String value, HLTBoolExpression expression)
	{
		values.put(value, expression)
	}

	def HLTBoolExpression getConditionForValue(String value)
	{
		return values.get(value)
	}

	def getValues()
	{
		return values
	}

	override getAllValues()
	{
		val all = new ArrayList<ConditionalValue>
		for (pair : values.entrySet)
		{
			all.add(new ConditionalValue(pair.key, pair.value))
		}
		return all
	}

	def assign(ExpressionResult newValue, HLTBoolExpression curCondition)
	{
		for (eachCondVal : newValue.allValues)
		{
			values.put(eachCondVal.value, eachCondVal.condition.and(curCondition))
		}
	}

	override compare(ExpressionResult that, HLTCompareRelation relation)
	{
		var HLTBoolExpression expr = new HLTDummyBoolExpression

		// Replace this expression with expressions where local variable fulfills this condition.
		val leftValues = this.getAllValues()
		val rightValues = that.getAllValues()

		for (leftValue : leftValues)
		{
			for (rightValue : rightValues)
			{
				val onePossibility = leftValue.condition.and(rightValue.condition).and(
					HLTConstantExpression.evaluate(leftValue.value, relation, rightValue.value))
				expr = expr.or(onePossibility)
			}
		}

		return expr
	}

}

class Constant implements ExpressionResult
{
	String value

	new(String value)
	{
		this.value = value
	}

	override getAllValues()
	{
		val all = new ArrayList<ConditionalValue>
		all.add(new ConditionalValue(value, HLTConstantExpression.alwaysTrue))
		return all
	}

	override compare(ExpressionResult that, HLTCompareRelation relation)
	{
		if (that instanceof Constant) // if both Constant then expand directly.
		{
			return HLTConstantExpression.evaluate((that as Constant).value, relation, this.value)
		}
		else
		{
			return that.compare(this, relation)
		}
	}

}

class GlobalVariable implements ExpressionResult
{
	String name

	new(String name)
	{
		this.name = name
	}

	override getAllValues()
	{
		val all = new ArrayList<ConditionalValue>
		all.add(new ConditionalValue(name, HLTConstantExpression.alwaysTrue))
		return all
	}

	def String getName()
	{
		return name
	}

	override compare(ExpressionResult that, HLTCompareRelation relation)
	{
		if (that instanceof GlobalVariable) // if both Constant then expand directly.
		{
			new HLTCompareExpression(this.name, HLTCompareRelation.Equals, (that as GlobalVariable).name)
		}
		else
		{
			return that.compare(this, relation)
		}
	}

}

class ConditionalValue
{
	public String value
	public HLTBoolExpression condition

	new(String value, HLTBoolExpression condition)
	{
		this.value = value
		this.condition = condition
	}
}
