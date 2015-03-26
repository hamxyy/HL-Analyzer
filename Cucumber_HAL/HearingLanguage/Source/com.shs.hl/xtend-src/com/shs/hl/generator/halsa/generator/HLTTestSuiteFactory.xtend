package com.shs.hl.generator.halsa.generator

import com.shs.hl.generator.halsa.exception.AbondonPathException
import com.shs.hl.generator.halsa.exception.InvalidOperationException
import com.shs.hl.generator.halsa.exception.UnreachablePathException
import com.shs.hl.generator.halsa.struct.ConditionalValuesForTest
import com.shs.hl.generator.halsa.struct.HLTBoolExpression
import com.shs.hl.generator.halsa.struct.HLTCompareExpression
import com.shs.hl.generator.halsa.struct.HLTConditionNode
import com.shs.hl.generator.halsa.struct.HLTConstantExpression
import com.shs.hl.generator.halsa.struct.HLTControlFlowTree
import com.shs.hl.generator.halsa.struct.HLTControlFlowTreeNode
import com.shs.hl.generator.halsa.struct.HLTExecutionNode
import com.shs.hl.generator.halsa.struct.HLTExecutionPath
import com.shs.hl.generator.halsa.struct.HLTGeneratedTestCase
import com.shs.hl.generator.halsa.struct.HLTGeneratedTestSuite
import com.shs.hl.generator.halsa.struct.HLTLogicalExpression
import com.shs.hl.generator.halsa.struct.HLTReturnNode
import com.shs.hl.generator.halsa.struct.IntegerForTest
import java.util.ArrayList
import java.util.HashMap
import java.util.List
import java.util.Map

class HLTTestSuiteFactory
{
	HLTGeneratedTestSuite curSuite

	// Stores all the possible values for a certain variable
	static Map<String, List<String>> possibleValues = new HashMap

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
			try
			{
				if (!path.hasNoEffect())
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
							throw new UnreachablePathException("Logic conflicting or unreachable!")
						}
						testCase.globalValues.put(entry.key, entry.value.firstOrDefault)
					}
					testCase.expectedValues = path.valuesChanged
					testCase.returnValue = path.valueReturned

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
			catch (AbondonPathException e)
			{
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
			if (node instanceof HLTReturnNode)
			{
				var returnNode = node as HLTReturnNode
				path.valueReturned = returnNode.value
			}
		}

		for (node : parent.children)
		{
			if (node instanceof HLTConditionNode)
			{
				deterministic = false
				val condition = (node as HLTConditionNode).condition
				var subPaths = fulfillCondition(path, condition)
				for (subPath : subPaths)
				{
					walkTreeNodeRecur(node, subPath, execPaths)
				}
			}
		}

		if (deterministic)
		{
			execPaths.add(path)
		}
	}

	private def List<HLTExecutionPath> fulfillCondition(HLTExecutionPath path, HLTBoolExpression boolExpr)
	{
		val subPaths = new ArrayList<HLTExecutionPath>

		switch (boolExpr)
		{
			case boolExpr instanceof HLTCompareExpression:
			{
				var subPath = path.fork
				var globalValues = subPath.valuesRead
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
						if (possibleValues.containsKey(compExpr.left.toString)) // This means it's an enum value.
						{
							val values = possibleValues.get(compExpr.left.toString)
							globalValues.put(compExpr.left.toString,
								new ConditionalValuesForTest(values).withCondition(
									[s|!s.equals(compExpr.right.toString)]))
						}
						else
						{
							val list = new ArrayList<Integer>()
							list.add(Integer.parseInt(compExpr.right))
							globalValues.put(compExpr.left.toString, new IntegerForTest(list))
						}

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

				subPaths.add(subPath)
			}
			case boolExpr instanceof HLTLogicalExpression:
			{
				val logicalExpr = boolExpr as HLTLogicalExpression
				switch (logicalExpr.relation)
				{
					case And:
					{
						var leftPaths = fulfillCondition(path, logicalExpr.left)
						for (leftPath : leftPaths)
						{
							subPaths.addAll(fulfillCondition(path, logicalExpr.right))
						}
					}
					case Or:
					{
						subPaths.addAll(fulfillCondition(path, logicalExpr.left))
						subPaths.addAll(fulfillCondition(path, logicalExpr.right))
					}
				}
			}
			case boolExpr instanceof HLTConstantExpression:
			{
				if (boolExpr.evaluate)
				{
					subPaths.add(path)
				}
			}
			default:
			{
				throw new InvalidOperationException("")
			}
		}
		return subPaths
	}

	private def String generateTestCase(HLTGeneratedTestCase testCase)
	'''
		TestCase «testCase.name» For «testCase.macroName»->«testCase.functionName»(«toParamString(testCase.paramList)»)
			«IF testCase.globalValues.size>0»
			When
				«FOR p : testCase.globalValues.entrySet»
					«p.key» = «p.value.toString»
				«ENDFOR»
			«ENDIF»
			Then
				«FOR p : testCase.expectedValues.entrySet»
					«p.key» = «p.value.toString»
				«ENDFOR»
			«IF testCase.returnValue != null»
				Return
					«testCase.returnValue»
			«ENDIF»
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
