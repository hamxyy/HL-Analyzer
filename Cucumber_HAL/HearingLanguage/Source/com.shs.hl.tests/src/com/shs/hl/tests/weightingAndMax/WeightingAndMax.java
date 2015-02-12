package com.shs.hl.tests.weightingAndMax;

import org.junit.Test;

import com.shs.hl.HearingLanguageStandaloneSetup;
import com.shs.hl.tests.R;

import de.itemis.xtext.typesystem.testing.XTextTestCase;

public class WeightingAndMax extends XTextTestCase {

	@Test
	public void testTypesOfParams() throws Exception {
		initializeAndGetRoot(new HearingLanguageStandaloneSetup(), 
				R.modelroot+"/weightingAndMax/weightingAndMax.hl", 
				R.allParameterFiles);

		assertConstraints( allIssues.errorsOnly().sizeIs(0) );
		
	}
	
}
