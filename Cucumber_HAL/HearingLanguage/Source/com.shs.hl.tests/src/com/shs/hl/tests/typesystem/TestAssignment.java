package com.shs.hl.tests.typesystem;

import org.junit.Test;

import com.shs.hl.HearingLanguageStandaloneSetup;
import com.shs.hl.hearingLanguage.AssignmentStatement;
import com.shs.hl.tests.R;

import de.itemis.xtext.typesystem.testing.XTextTestCase;

public class TestAssignment extends XTextTestCase {

	@Test
	public void testIssues() throws Exception {
		initializeAndGetRoot(new HearingLanguageStandaloneSetup(), 
				R.modelroot+"/typesystem/testAssignment.hl", 
				R.allParameterFiles);
		
		assertConstraints( allIssues.errorsOnly().sizeIs(2) );

		assertConstraints( allIssues.forType(AssignmentStatement.class).oneOfThemContains("incompatible type") );
		assertConstraints( allIssues.forType(AssignmentStatement.class).oneOfThemContains("cannot assign") );
		
	}
	
	

}
