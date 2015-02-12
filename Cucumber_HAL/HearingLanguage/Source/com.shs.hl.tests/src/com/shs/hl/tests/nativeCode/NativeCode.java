//package com.shs.hl.tests.nativeCode;
//
//import org.junit.Test;
//
//import com.shs.hl.HearingLanguageStandaloneSetup;
//import com.shs.hl.hearingLanguage.DelegateNative;
//import com.shs.hl.hearingLanguage.FunctionDeclaration;
//import com.shs.hl.tests.R;
//
//import de.itemis.xtext.typesystem.testing.XTextTestCase;
//
//public class NativeCode extends XTextTestCase {
//
//	@Test
//	public void testTypesOfParams() throws Exception {
//		initializeAndGetRoot(new HearingLanguageStandaloneSetup(), 
//				R.modelroot+"/nativeCode/nativeCode.hl");
//
//	
//		allIssues.dumpIssues();
//		assertConstraints( allIssues.errorsOnly().sizeIs(3) );
//
//		assertConstraints( allIssues.forElement(FunctionDeclaration.class,"f2").theOneAndOnlyContains("contain the delegateNative") );
//		assertConstraints( allIssues.forElement(FunctionDeclaration.class,"f3").theOneAndOnlyContains("contain the delegateNative") );
//		assertConstraints( allIssues.forType(DelegateNative.class).theOneAndOnlyContains("can only appear in native") );
//
//		
//	}
//	
//}
