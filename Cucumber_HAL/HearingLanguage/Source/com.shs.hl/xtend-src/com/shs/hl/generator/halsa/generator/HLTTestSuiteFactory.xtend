package com.shs.hl.generator.halsa.generator

import com.shs.hl.generator.halsa.exception.InvalidOperationException
import com.shs.hl.generator.halsa.struct.HLTBoolExpression
import com.shs.hl.generator.halsa.struct.HLTCompareExpression
import com.shs.hl.generator.halsa.struct.HLTConditionNode
import com.shs.hl.generator.halsa.struct.HLTControlFlowTree
import com.shs.hl.generator.halsa.struct.HLTControlFlowTreeNode
import com.shs.hl.generator.halsa.struct.HLTExecutionNode
import com.shs.hl.generator.halsa.struct.HLTExecutionPath
import com.shs.hl.generator.halsa.struct.HLTGeneratedTestCase
import com.shs.hl.generator.halsa.struct.HLTGeneratedTestSuite
import com.shs.hl.generator.halsa.struct.HLTLogicalExpression
import java.util.ArrayList
import java.util.HashMap
import java.util.List
import java.util.Map
import com.shs.hl.generator.halsa.struct.ValuesForTest
import com.shs.hl.generator.halsa.struct.ConditionalValuesForTest

class HLTTestSuiteFactory
{
	HLTGeneratedTestSuite curSuite

	// Stores all the possible values for a certain variable
	Map<String, List<String>> possibleValues = new HashMap

	def forMacro(String macroName)
	{
		curSuite = new HLTGeneratedTestSuite
		curSuite.macroName = macroName
	}

	def forFunction(String functionName, List<String> args, HLTControlFlowTree tree)
	{
		val execPaths = new ArrayList<HLTExecutionPath>
		walkTreeNodeRecur(tree.root, new HLTExecutionPath, execPaths)

		for (path : execPaths)
		{
			val testCase = curSuite.createTestCase()
			testCase.name = functionName + "_testcase_" + (curSuite.testCases.length + 1)
			testCase.functionName = functionName
			args.forEach [ n |
				{
					var value = path.valuesRead.get(n)
					if (value == null)
					{
						testCase.paramList.add("?")
					}
					else
					{
						testCase.paramList.add(value.firstOrDefault)
						path.valuesRead.remove(n)
					}
				}
			]
			for (entry : path.valuesRead.entrySet)
			{
				if (entry.value.firstOrDefault == null)
				{
					throw new Exception("Logic conflicting!")
				}
				testCase.globalValues.put(entry.key, entry.value.firstOrDefault)
			}
			testCase.expectedValues = path.valuesChanged

			var merged = false
			for (var i = 0; i < curSuite.testCases.length; i++)
			{
				if (!merged)
				{
					val mergedTestCase = curSuite.testCases.get(i).merge(testCase)
					if (mergedTestCase != null)
					{
						curSuite.testCases.set(i, mergedTestCase)
						merged = true
					}
				}
			}
			if (!merged)
			{
				curSuite.testCases.add(testCase)
			}
		}
	}

	def void walkTreeNodeRecur(HLTControlFlowTreeNode parent, HLTExecutionPath path, List<HLTExecutionPath> execPaths)
	{
		var deterministic = true
		for (node : parent.children)
		{
			if (node instanceof HLTExecutionNode)
			{
				val exeNode = node as HLTExecutionNode
				path.valuesChanged.put(exeNode.id, exeNode.value)
			}
		}

		for (node : parent.children)
		{
			if (node instanceof HLTConditionNode)
			{
				deterministic = false
				var subPath = path.fork
				val condition = (node as HLTConditionNode).condition

				//				if (condition.evaluate())
				//				{
				//				}
				fulfillCondition(subPath.valuesRead, condition)
				walkTreeNodeRecur(node, subPath, execPaths)
			}
		}

		if (deterministic)
		{
			execPaths.add(path)
		}
	}

	private def void fulfillCondition(Map<String, ValuesForTest> globalValues, HLTBoolExpression boolExpr)
	{
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
						globalValues.put(compExpr.left.toString, new ConditionalValuesForTest(values))
					}
					case Unequals:
					{
						val values = possibleValues.get(compExpr.left.toString)
						globalValues.put(compExpr.left.toString,
							new ConditionalValuesForTest(values).withCondition([s|!s.equals(compExpr.right.toString)]))
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
						fulfillCondition(globalValues, logicalExpr.left)
						fulfillCondition(globalValues, logicalExpr.right)
					}
					case Or:
					{
						fulfillCondition(globalValues, logicalExpr.left)
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
	'''«FOR testCase : curSuite.testCases»
			«generateTestCase(testCase)»
		«ENDFOR»
		'''

	private def toParamString(List<Object> objects)
	{
		var result = ""
		for (obj : objects)
		{
			result += obj.toString + ","
		}
		if (result.length > 0)
		{
			result = result.substring(0, result.length - 1)
		}
		return result
	}

	def addPossibleValues(String paramName, List<String> values)
	{
		possibleValues.put(paramName, values)
	}

}
