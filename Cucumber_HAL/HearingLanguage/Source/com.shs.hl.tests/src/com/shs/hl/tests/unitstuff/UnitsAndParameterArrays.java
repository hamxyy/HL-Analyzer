package com.shs.hl.tests.unitstuff;

import org.junit.Test;

import com.shs.hl.HearingLanguageStandaloneSetup;
import com.shs.hl.tests.R;

import de.itemis.xtext.typesystem.testing.XTextTestCase;

public class UnitsAndParameterArrays extends XTextTestCase {

	@Test
	public void testTypesOfParams() throws Exception {
		initializeAndGetRoot(new HearingLanguageStandaloneSetup(), 
				R.modelroot+"/unitstuff/unitsAndParameterArrays.hl", 
				R.allParameterFiles);
		
		assertConstraints( allIssues.errorsOnly().sizeIs(0) );
		
	}
	
}
