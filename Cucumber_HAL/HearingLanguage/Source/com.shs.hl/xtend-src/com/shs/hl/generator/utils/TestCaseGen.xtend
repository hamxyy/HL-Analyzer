package com.shs.hl.generator.utils

import com.shs.hl.hearingLanguage.Namespace



class TestCaseGen {
	
	def static CharSequence genTest(Namespace nmspace){
	'''
   #region testfunctions for «nmspace.name»
   [TestCount]
   public int _TestCounter{get; set;}
   
   [TestFailCount]
   public int _TestFailed {get;set;}
   
   [TestResult]
   public IDictionary<string,bool> _TestStates{get; private set;}
   
   [TestFailDetail]
   public IDictionary<string,string> _TestInformation {get; private set;}
     
   
   public IDictionary<string,int> _TestDataInformation {get; private set;}
   
   
   public int _AssertGet(string key=null)
   {
   		if (String.IsNullOrEmpty(key)) return -1;
   		
   		if (_TestDataInformation==null) return -1;
   		
   		int val;
   		if (!_TestDataInformation.TryGetValue(key,out val)) return -1;
   		return val;
   }
   
   
   public void _AssertSet(string key, int value)
   {
   		if (_TestDataInformation==null)
   		{
   			_TestDataInformation = new Dictionary<string,int>();
   		}
   		if (String.IsNullOrEmpty(key)) return;
   		
   		_TestDataInformation[key]=value;
   		
   }
   
   private void _AddTestResult(string name, bool testResult, string detail=null)
   {
   		_TestCounter++;
   		if (_TestStates==null)
        {
           _TestStates= new Dictionary<string, bool>();
        }
   		if(_TestInformation==null)
        {
           _TestInformation=new Dictionary<string, string>();
        }
   		
   		
   		if (!testResult)
   		{
   		 	_TestFailed++;
   		 	if(!String.IsNullOrEmpty(detail))
   		 	{
   		 		
   		 		_TestInformation[name]=detail;
   		 	}   		 	
   		}
   		_TestStates[name]=testResult;
   }
   
   [TestReset]
   public void _Reset()
   {
   	   _TestCounter=0;
   	   _TestFailed=0;
   	   _TestStates.Clear();
   	   _TestInformation.Clear();
   }
   
   
   private void _Assert(string id,bool expression, string info=null)
   {
   		_AddTestResult(id,expression,info);   		
   }
   
   
   private void _AssertEquals(string id, object expected, object actalVal)
   {
   		bool testResult=false;
   		
   		if (expected==null)
   		{
   			if (actalVal==null)
   			{
   				testResult=true;
   			}
   		}
   		else
   		{
   			testResult = expected.Equals(actalVal);
   		}
   		
   		
   		string detailInfo=null;
   		if (!testResult)
   		{
   			detailInfo ="expected: "+expected+"   actual: "+actalVal;
   		}  
   		_AddTestResult(id,testResult,detailInfo);		
   }
   
   private void _Fail(string id)
   {
   		_AddTestResult(id,false,"Test failed");
   }
#endregion 	
		'''
 }
	
}