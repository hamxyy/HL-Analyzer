package com.shs.hl.generator.halsa.struct

import com.shs.hl.generator.halsa.exception.OnlyHappenWhenBuggyException
import java.util.ArrayList
import java.util.HashMap
import java.util.List
import java.util.Map

class HLTGeneratedTestSuite
{
	public String macroName;
	public List<HLTGeneratedTestCase> testCases = new ArrayList;

	def HLTGeneratedTestCase createTestCase()
	{
		var result = new HLTGeneratedTestCase
		result.macroName = macroName
		return result
	}

	def String getFileName()
	{
		return macroName + "_generated.hlt"
	}
}

class HLTGeneratedTestCase
{
	public String name;
	public String macroName;
	public String functionName;
	public String returnValue;
	public List<Object> paramList = new ArrayList;
	public Map<String, String> globalValues = new HashMap;
	public Map<String, String> expectedValues = new HashMap;

	def HLTGeneratedTestCase merge(HLTGeneratedTestCase another)
	{
		if (containsSameConditions(another))
		{
			var merged = new HLTGeneratedTestCase
			merged.name = name
			merged.macroName = macroName
			merged.functionName = functionName
			merged.returnValue = returnValue
			merged.paramList = paramList
			merged.globalValues = globalValues
			merged.expectedValues = expectedValues.merge(another.expectedValues)
			return merged
		}
		else if (another.containsSameConditions(this))
		{
			var merged = new HLTGeneratedTestCase
			merged.macroName = macroName
			merged.functionName = functionName
			merged.paramList = paramList
			merged.globalValues = another.globalValues
			merged.expectedValues = expectedValues.merge(another.expectedValues)
			return merged
		}

		return null
	}

	def private Map<String, String> merge(Map<String, String> first, Map<String, String> second)
	{
		val merged = new HashMap
		for (entry : first.entrySet)
		{
			if (second.containsKey(entry.key) && !entry.value.equals(second.get(entry.key)))
			{
				throw new OnlyHappenWhenBuggyException("Conflicting expectations!")
			}
			merged.put(entry.key, entry.value)
		}

		for (entry : second.entrySet)
		{
			merged.put(entry.key, entry.value)
		}
		return merged
	}

	def boolean containsSameConditions(HLTGeneratedTestCase another)
	{
		if (!macroName.equals(another.macroName) || !functionName.equals(another.functionName))
		{
			return false
		}
		for (entry : another.globalValues.entrySet)
		{
			if (!entry.value.equals(globalValues.get(entry.key)))
			{
				return false
			}
		}

		for (var i = 0; i < another.paramList.length; i++)
		{
			if (!another.paramList.get(i).equals(paramList.get(i)))
			{
				return false
			}
		}

		if (this.returnValue != null && !this.returnValue.equals(another.returnValue))
		{
			return false
		}
		return true
	}

}

class HLTExecutionPath
{
	public Map<String, ValuesForTest> valuesRead = new HashMap
	public Map<String, String> valuesChanged = new HashMap
	public String valueReturned

	def fork()
	{
		var cloned = new HLTExecutionPath
		cloned.valuesRead = new HashMap
		for (entry : valuesRead.entrySet)
		{
			cloned.valuesRead.put(entry.key, entry.value)
		}
		cloned.valuesChanged = new HashMap
		for (entry : valuesChanged.entrySet)
		{
			cloned.valuesChanged.put(entry.key, entry.value)
		}
		cloned.valueReturned = valueReturned
		return cloned
	}

	def hasNoEffect()
	{
		return valueReturned == null && valuesChanged.isEmpty
	}

}
