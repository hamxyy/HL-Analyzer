package com.shs.hl.generator.halsa

import java.util.HashMap
import java.util.Map

class MacroBoundaryValuesCollection
{
	Map<String, FunctionBoundaryValuesCollection> functionValues = new HashMap
	String currentFuncName

	def void forFunction(String funcName)
	{
		currentFuncName = funcName
	}

	def FunctionBoundaryValuesCollection current()
	{
		if (!functionValues.containsKey(currentFuncName))
		{
			functionValues.put(currentFuncName, new FunctionBoundaryValuesCollection)
		}
		return functionValues.get(currentFuncName)
	}

	def String forPrint()
	{
		var sb = new StringBuilder
		for (funcEntry : functionValues.entrySet)
		{
			sb.append(funcEntry.key + " {\n")
			for (scopeBoundaryValuesEntry : funcEntry.value.entrySet)
			{
				sb.append("  " + scopeBoundaryValuesEntry.key + " {\n")
				for (innerEntry : scopeBoundaryValuesEntry.value.entrySet)
				{
					sb.append(
						"    " + innerEntry.key + " : " + innerEntry.value.value + ",     #" + innerEntry.value.location +
							"\n")
				}
				sb.append("  }\n")
			}
			sb.append("}\n")
		}

		return sb.toString
	}

}

class FunctionBoundaryValuesCollection
{
	Map<String, ScopeBoundaryValues> values = new HashMap

	def void put(String scope, String id, String value)
	{
		val scopeValues = getScopeValues(scope)
		val boundaryValue = new SingleBoundaryValue(id)
		boundaryValue.value = value
		scopeValues.put(boundaryValue)
	}

	def void putWithLocation(String scope, String id, String value, String location)
	{
		val scopeValues = getScopeValues(scope)
		val boundaryValue = new SingleBoundaryValue(id)
		boundaryValue.value = value
		boundaryValue.location = location
		scopeValues.put(boundaryValue)
	}

	def private getScopeValues(String scope)
	{
		if (!values.containsKey(scope))
		{
			values.put(scope, new ScopeBoundaryValues(scope))
		}
		return values.get(scope)
	}

	def getValues()
	{
		return values
	}

	def entrySet()
	{
		return values.entrySet
	}

}

class ScopeBoundaryValues
{
	public String scope
	public Map<String, SingleBoundaryValue> values = new HashMap

	new(String scope)
	{
		this.scope = scope
	}

	def put(SingleBoundaryValue boundaryValue)
	{
		values.put(boundaryValue.id, boundaryValue)
	}

	def entrySet()
	{
		return values.entrySet
	}

}

class SingleBoundaryValue
{
	public String id
	public String value
	public String location = ""

	new(String id)
	{
		this.id = id
	}
}
