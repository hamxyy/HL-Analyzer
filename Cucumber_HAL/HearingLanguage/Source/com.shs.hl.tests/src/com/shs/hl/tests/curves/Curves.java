package com.shs.hl.tests.curves;

import org.junit.Test;

import com.shs.hl.HearingLanguageStandaloneSetup;
import com.shs.hl.hearingLanguage.BuiltInCall;
import com.shs.hl.tests.R;

import de.itemis.xtext.typesystem.testing.XTextTestCase;

public class Curves extends XTextTestCase {

	@Test
	public void testTypesOfParams() throws Exception {
		initializeAndGetRoot(new HearingLanguageStandaloneSetup(), 
				R.modelroot+"/curves/curves.hl",
				R.allParameterFilesAndBuiltIns);
		
		assertConstraints( allIssues.errorsOnly().sizeIs(1) );
		
		assertConstraints( allIssues.forType(BuiltInCall.class).theOneAndOnlyContains("cannot be assigned") );
		
	}
	
}
