package com.shs.hl.generator.halsa.struct

import java.util.ArrayList
import java.util.HashMap
import java.util.List
import java.util.Map

interface ExpressionResult
{
	def List<ConditionalValue> getAllValues()

	/**
	 * This method will evaluate the result if possible.
	 */
	def HLTBoolExpression compare(ExpressionResult that, HLTCompareRelation relation)

}

class LocalVariable implements ExpressionResult
{
	VariableScope scope = new VariableScope()

	override getAllValues()
	{
		return scope.allValues
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

	def assign(ExpressionResult newValue)
	{
		scope.assign(newValue.allValues)
	}

	def plusEquals(String increment)
	{
		scope.plusEquals(increment)
	}

	def enterNewScope(HLTBoolExpression condition)
	{
		scope = scope.enterNewScope(condition)
	}

	def exitScope()
	{
		scope = scope.exitScope
	}

	override toString()
	{
		return scope.toString
	}

}

class Constant implements ExpressionResult
{
	String value

	new(String value)
	{
		this.value = value
	}

	def getValue()
	{
		return value
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

	override toString()
	{
		return value
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
			new HLTCompareExpression(this.name, relation, (that as GlobalVariable).name)
		}
		else if (that instanceof Constant) // if that is a constant, then compare directly
		{
			new HLTCompareExpression(this.name, relation, (that as Constant).value)
		}
		else // local variable, then let variable compare
		{
			return that.compare(this, relation)
		}
	}

	override toString()
	{
		return name
	}
}

class TempExpressionResult<T> implements ExpressionResult
{
	T value

	new(T value)
	{
		this.value = value
	}

	def T getValue()
	{
		return value as T
	}

	override getAllValues()
	{
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}

	override compare(ExpressionResult that, HLTCompareRelation relation)
	{
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
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

	override toString()
	{
		return value + "=" + condition
	}
}

class VariableScope
{
	HLTBoolExpression scopeCondition = HLTConstantExpression.alwaysTrue
	ConditionalValueMap values = new ConditionalValueMap
	List<VariableScope> nestedScopes = new ArrayList
	VariableScope parentScope

	def VariableScope enterNewScope(HLTBoolExpression condition)
	{
		val cloned = new ConditionalValueMap
		for (possibleValue : allValues)
		{
			cloned.put(possibleValue.value, possibleValue.condition)
		}
		val newScope = new VariableScope
		newScope.scopeCondition = condition.and(this.scopeCondition)
		newScope.values = cloned
		newScope.parentScope = this
		this.nestedScopes.add(newScope)
		return newScope
	}

	def VariableScope exitScope()
	{
		return this.parentScope
	}

	def assign(List<ConditionalValue> newValues)
	{

		// Override whatever value it had before
		nestedScopes.clear
		values.clear
		for (newCondValue : newValues)
		{
			values.put(newCondValue.value, newCondValue.condition.and(scopeCondition))
		}
	}

	def plusEquals(String increment)
	{
		val allValues = this.getAllValues()
		nestedScopes.clear
		values.clear
		for (value : allValues)
		{
			values.put(String.valueOf(value.value.toInt + increment.toInt), value.condition.and(scopeCondition))
		}
	}

	def List<ConditionalValue> getAllValues()
	{
		val all = new ArrayList<ConditionalValue>
		for (pair : values.entrySet)
		{
			all.add(new ConditionalValue(pair.key, pair.value))
		}
		for (nestedScope : nestedScopes)
		{
			all.addAll(nestedScope.allValues)
		}
		return all
	}

	private def toInt(String string)
	{
		return Integer.parseInt(string)
	}

	override toString()
	{
		return allValues.toString
	}
}

class ConditionalValueMap
{
	Map<String, HLTBoolExpression> values = new HashMap

	def clear()
	{
		values.clear
	}

	def entrySet()
	{
		return values.entrySet
	}

	def put(String key, HLTBoolExpression value)
	{
		if (values.containsKey(key))
		{
			values.put(key, values.get(key).or(value))
		}
		else
		{
			values.put(key, value)
		}
	}

	override toString()
	{
		return values.toString
	}
}
