package com.shs.hl.generator.halsa.testcase

import com.google.common.base.Function
import java.util.ArrayList
import java.util.List

interface ValueToUse
{
	def String firstOrDefault()

	def String select(Function<String, Boolean> predicate)

	def ValueToUse withCondition(Function<String, Boolean> newCond)
}

class ConditionalValueToUse implements ValueToUse
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

}
//
//class EnumValueToUse implements ValueToUse
//{
//	List<String> all
//
//	new(List<String> all)
//	{
//		this.all = all
//	}
//
//	private def String selectOne()
//	{
//		return all.get(0)
//	}
//
//	override getValue()
//	{
//		return selectOne()
//	}
//
//	override select(Function<String, Boolean> predicate)
//	{
//		for (each : all)
//		{
//			if (predicate.apply(each))
//			{
//				return each
//			}
//		}
//		return null
//	}
//
//}
