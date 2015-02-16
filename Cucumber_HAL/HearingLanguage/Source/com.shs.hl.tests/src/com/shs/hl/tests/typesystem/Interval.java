package com.shs.hl.tests.typesystem;

import org.junit.Test;

import com.shs.hl.HearingLanguageStandaloneSetup;
import com.shs.hl.tests.R;

import de.itemis.xtext.typesystem.testing.XTextTestCase;

public class Interval extends XTextTestCase {

	@Test
	public void testIssues() throws Exception {
		initializeAndGetRoot(new HearingLanguageStandaloneSetup(), 
				R.modelroot+"/typesystem/interval.hl");

		assertConstraints( allIssues.errorsOnly().sizeIs(0) );
		
	}
	
	

}