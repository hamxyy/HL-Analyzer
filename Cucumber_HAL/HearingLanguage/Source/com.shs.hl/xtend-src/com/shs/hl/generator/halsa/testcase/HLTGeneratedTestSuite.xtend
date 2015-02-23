package com.shs.hl.generator.halsa.testcase

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
	public List<Object> paramList = new ArrayList;
	public Map<String, String> globalValues = new HashMap;
	public Map<String, String> expectedValues = new HashMap;
}

class HLTExecutionPath
{
	public Map<String, ValueToUse> valuesRead = new HashMap
	public Map<String, String> valuesChanged = new HashMap

	def fork()
	{
		var cloned = new HLTExecutionPath
		cloned.valuesRead = valuesRead.deepClone
		for (entry : valuesRead.entrySet)
		{
			cloned.valuesRead.put(entry.key, entry.value)
		}
		cloned.valuesChanged = new HashMap
		for (entry : valuesChanged.entrySet)
		{
			cloned.valuesChanged.put(entry.key, entry.value)
		}
		return cloned
	}

	def deepClone(List<Object> objects)
	{
		var result = new ArrayList
		for (each : objects)
		{
			result.add(each)
		}
		return result
	}

	def deepClone(Map<String, ValueToUse> map)
	{
		var result = new HashMap
		for (entry : map.entrySet)
		{
			result.put(entry.key, entry.value)
		}
		return result
	}
}
