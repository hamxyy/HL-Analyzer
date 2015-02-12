package com.shs.hl.tests.scopes;

import org.junit.Test;

import com.shs.hl.HearingLanguageStandaloneSetup;
import com.shs.hl.tests.R;

import de.itemis.xtext.typesystem.testing.XTextTestCase;

public class TestScopes1 extends XTextTestCase {

	@Test
	public void testScopes() throws Exception {
		initializeAndGetRoot(new HearingLanguageStandaloneSetup(), R.modelroot+"/scopes/testScopes1.hl");

		assertConstraints( allIssues.errorsOnly().sizeIs(0) ); 

		
	}
	
	
	
}
