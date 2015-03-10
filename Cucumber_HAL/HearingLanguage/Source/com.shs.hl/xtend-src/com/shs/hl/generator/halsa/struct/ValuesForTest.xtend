package com.shs.hl.generator.halsa.struct

import com.google.common.base.Function
import java.util.ArrayList
import java.util.List

interface ValuesForTest
{
	def String firstOrDefault()
}

class IntegerForTest implements ValuesForTest
{
	List<Integer> notAllowedValues

	new(List<Integer> notAllowedValues)
	{
		this.notAllowedValues = notAllowedValues
	}

	override firstOrDefault()
	{
		if (notAllowedValues == null || notAllowedValues.isEmpty)
		{
			return "0"
		}
		return (notAllowedValues.max + 1).toString
	}
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
		if (all == null)
		{
			return null
		}

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

			viable = true
		}
		return null
	}

	//	override select(Function<String, Boolean> exPredicate)
	//	{
	//		if (all == null)
	//		{
	//			return null
	//		}
	//		for (each : all)
	//		{
	//			if (exPredicate.apply(each))
	//			{
	//				for (predicate : predicates)
	//				{
	//					if (predicate.apply(each))
	//					{
	//
	//						// Return the first viable one
	//						return each
	//					}
	//				}
	//
	//			}
	//		}
	//		return null
	//	}
	def withCondition(Function<String, Boolean> newCond)
	{
		predicates.add(newCond)
		return this
	}

	override toString()
	{
		return this.firstOrDefault + " : " + this.all.toString + ""
	}

}
