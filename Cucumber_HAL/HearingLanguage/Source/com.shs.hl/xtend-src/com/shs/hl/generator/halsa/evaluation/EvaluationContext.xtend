package com.shs.hl.generator.halsa.evaluation

import java.util.HashMap
import java.util.Map
import java.util.Stack

class EvaluationContext
{
	Map<String, String> globalValues = new HashMap
	Stack<Map<String, Object>> allValues = new Stack
	StringBuilder builder = new StringBuilder

	new(Map<String, String> globals)
	{
		this.globalValues = globals
	}

	new()
	{
	}

	def void enterNewScope()
	{
		allValues.push(new HashMap)
	}

	def void exitScope()
	{
		allValues.pop()
	}

	def addLocalVar(String key, Object value)
	{
		allValues.peek.put(key, value)
	}

	def setGlobalVar(String key, String value)
	{
		globalValues.put(key, value)
	}

	def getLocal(String key)
	{
		return allValues.peek.get(key)
	}

	def getGlobal(String key)
	{
		var result = globalValues.get(key)
		if(result == null)
		{
			builder.append("Missing " + key + ", Use default value. \n")
			result = ""
		}
		return result
	}
	
	def errorLog()
	{
		return builder.toString
	}
}
