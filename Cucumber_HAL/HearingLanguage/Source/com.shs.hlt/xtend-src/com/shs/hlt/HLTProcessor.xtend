package com.shs.hlt

import com.shs.hl.hearingLanguageTest.TestCase
import com.shs.hl.hearingLanguageTest.ThenBlock
import com.shs.hl.hearingLanguageTest.WhenBlock
import java.util.ArrayList
import java.util.HashMap
import java.util.List
import java.util.Map
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.generator.IFileSystemAccess

class HLTProcessor
{
	def HLTTestSuite parseTestSuite(Resource resource, IFileSystemAccess fsa)
	{
		var suite = new HLTTestSuite
		suite.testCases = new ArrayList
		var hltTestCase = new HLTTestCase

		for (testCaseAST : resource.allContents.toIterable.filter(typeof(TestCase)))
		{
			hltTestCase = new HLTTestCase
			hltTestCase.globalValues = new HashMap<String, String>
			hltTestCase.expectedValues = new HashMap<String, String>

			hltTestCase.name = testCaseAST.name
			hltTestCase.macroName = testCaseAST.^for.macro
			hltTestCase.functionName = testCaseAST.^for.function
			hltTestCase.paramList = new ArrayList
			for (actualArg : testCaseAST.^for.actualArgs)
			{
				hltTestCase.paramList.add(convert(actualArg))
			}

			for (setup : (testCaseAST.when as WhenBlock).setups)
			{
				hltTestCase.globalValues.put(setup.scope.getName() + ":" + setup.param.name, setup.value.name)
			}

			for (assert : (testCaseAST.then as ThenBlock).asserts)
			{
				hltTestCase.expectedValues.put(assert.scope.getName() + ":" + assert.param.name, assert.value.name)
			}

			if ((testCaseAST.then as ThenBlock).returnValue != null)
			{
				hltTestCase.expectedValues.put("return", (testCaseAST.then as ThenBlock).returnValue)
			}

			suite.testCases.add(hltTestCase)
		}

		return suite
	}

	private def convert(String string)
	{
		if (string == "true")
		{
			return true;
		}
		else if (string == "false")
		{
			return false;
		}
		else
		{
			return Integer.parseInt(string);
		}
	}

}

class HLTTestCase
{
	public String name;
	public String macroName;
	public String functionName;
	public List<Object> paramList;
	public Map<String, String> globalValues;
	public Map<String, String> expectedValues;
}

class HLTTestResult
{
	public Map<String, String> failedAssertions = new HashMap
	public HLTTestCase testCase

	new(HLTTestCase testCase)
	{
		this.testCase = testCase
	}

	def boolean isPass()
	{
		return failedAssertions == null || failedAssertions.isEmpty
	}
}

public class HLTTestSuite
{
	public List<HLTTestCase> testCases;

}
