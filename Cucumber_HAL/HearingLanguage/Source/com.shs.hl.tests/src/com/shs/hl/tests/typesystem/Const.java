package com.shs.hl.tests.typesystem;

import org.junit.Test;

import com.shs.hl.HearingLanguageStandaloneSetup;
import com.shs.hl.hearingLanguage.AssignmentStatement;
import com.shs.hl.hearingLanguage.LocalVariableDeclaration;
import com.shs.hl.tests.R;

import de.itemis.xtext.typesystem.testing.XTextTestCase;

public class Const extends XTextTestCase {

	@Test
	public void testIssues() throws Exception {
		initializeAndGetRoot(new HearingLanguageStandaloneSetup(), 
				R.modelroot+"/typesystem/const.hl");

		assertConstraints( allIssues.errorsOnly().sizeIs(2) );
		
		assertConstraints( allIssues.forType(LocalVariableDeclaration.class).named("g").theOneAndOnlyContains("need an initialization") );
		assertConstraints( allIssues.forType(AssignmentStatement.class).theOneAndOnlyContains("cannot assign to const") );
		
	}
	
	

}
