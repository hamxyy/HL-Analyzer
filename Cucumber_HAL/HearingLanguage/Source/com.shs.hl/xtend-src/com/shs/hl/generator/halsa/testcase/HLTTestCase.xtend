package com.shs.hl.generator.halsa.testcase;

import com.shs.hl.generator.halsa.controltree.HLTCompareExpression
import com.shs.hl.generator.halsa.controltree.HLTConditionNode
import com.shs.hl.generator.halsa.controltree.HLTControlFlowTree
import com.shs.hl.generator.halsa.controltree.HLTControlTreeNode
import com.shs.hl.generator.halsa.controltree.HLTExecutionNode
import com.shs.hl.generator.halsa.controltree.HLTLogicalExpression
import com.shs.hl.generator.halsa.exception.InvalidOperationException
import java.util.ArrayList
import java.util.HashMap
import java.util.List
import java.util.Map

class HLTTestSuiteFactory
{
	HLTGeneratedTestSuite suite
	String functionName
	List<HLTExecutionPath> execPaths = new ArrayList

	def forMacro(String macroName)
	{
		suite = new HLTGeneratedTestSuite
		suite.fileName = macroName + "_generated.hlt"
	}

	def forFunction(String functionName)
	{
		this.functionName = functionName
	}

	def forControlTree(HLTControlFlowTree tree)
	{
		var HLTExecutionPath execPath = new HLTExecutionPath
		walkTreeNodeRecur(tree.root, execPath)
	}

	def void walkTreeNodeRecur(HLTControlTreeNode parent, HLTExecutionPath execPath)
	{
		var curPath = execPath
		var deterministic = true
		for (node : parent.children)
		{
			switch (node)
			{
				case node instanceof HLTConditionNode:
				{
					deterministic = false
					curPath = curPath.fork
					analyzeCondition(curPath.valuesRead, node as HLTConditionNode)
					walkTreeNodeRecur(node, curPath)
				}
				case node instanceof HLTExecutionNode:
				{
					val exeNode = node as HLTExecutionNode
					curPath.valuesChanged.put(exeNode.id, exeNode.value)
				}
			}
		}

		if (deterministic)
		{
			execPaths.add(curPath)
		}
	}

	private def analyzeCondition(Map<String, ValueToUse> globalValues, HLTConditionNode node)
	{
		val boolExpr = node.condition
		switch (boolExpr)
		{
			case boolExpr instanceof HLTCompareExpression:
			{
				val compExpr = boolExpr as HLTCompareExpression
				switch (compExpr.relation)
				{
					case Equals:
					{
						val values = new ArrayList<String>()
						values.add(compExpr.right.toString)
						globalValues.put(compExpr.left.toString, new ConditionalValueToUse(values))
					}
					case Unequals:
					{
						val values = new ArrayList<String>()
						values.add(compExpr.right.toString)
						globalValues.put(compExpr.left.toString, new ConditionalValueToUse(values))
					}
					case GreaterThan:
					{
					}
					case LessThan:
					{
					}
					case GreaterThanOrEquals:
					{
					}
					case LessThanOrEquals:
					{
					}
				}
			}
			case boolExpr instanceof HLTLogicalExpression:
			{
				val logicalExpr = boolExpr as HLTLogicalExpression
				switch (logicalExpr.relation)
				{
					case And:
					{
					}
					case Or:
					{
					}
				}
			}
			default:
			{
				throw new InvalidOperationException("")
			}
		}
	}

	private def String generateTestCase(HLTGeneratedTestCase testCase)
	'''
		TestCase «testCase.name» For «testCase.macroName»->«testCase.functionName»(«toParamString(testCase.paramList)»)
			When
				«FOR p : testCase.globalValues.entrySet»
					«p.key» = «p.value.toString»
				«ENDFOR»
			Then
				«FOR p : testCase.expectedValues.entrySet»
					«p.key» = «p.value.toString»
				«ENDFOR»
		End
	'''

	def String generate()
	'''«FOR testCase : suite.testCases»
			«generateTestCase(testCase)»
		«ENDFOR»
		'''

	def toParamString(List<Object> objects)
	{
		var result = ""
		for (obj : objects)
		{
			result += obj.toString + ","
		}
		if (result.length > 0)
		{
			result = result.substring(0, result.length - 2)
		}
		return result
	}

}

class HLTGeneratedTestSuite
{
	public String fileName;
	public List<HLTGeneratedTestCase> testCases = new ArrayList;
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
