package com.shs.hl.generator.halsa.struct

import com.google.common.base.Function
import java.util.ArrayList
import java.util.List

interface ValuesForTest
{
	def String firstOrDefault()

	def String select(Function<String, Boolean> predicate)

	def ValuesForTest withCondition(Function<String, Boolean> newCond)
}

class ConditionalValuesForTest implements ValuesForTest
{
	List<Function<String, Boolean>> predicates = new ArrayList
	List<String> all

	new(List<String> all)
	{
		this.all = all
	}

	override firstOrDefault()
	{
		var viable = true
		for (each : all)
		{
			for (predicate : predicates)
			{
				if (!predicate.apply(each))
				{
					viable = false
				}
			}

			if (viable)
			{
				return each
			}
		}
		return null
	}

	override select(Function<String, Boolean> exPredicate)
	{
		for (each : all)
		{
			if (exPredicate.apply(each))
			{
				for (predicate : predicates)
				{
					if (predicate.apply(each))
					{

						// Return the first viable one
						return each
					}
				}

			}
		}
		return null
	}

	override withCondition(Function<String, Boolean> newCond)
	{
		predicates.add(newCond)
		return this
	}

	override toString()
	{
		return this.firstOrDefault + " : " + this.all.toString + ""
	}

}
