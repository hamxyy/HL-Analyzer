package com.shs.hl.tests.text;

import org.junit.Test;

import com.shs.hl.HearingLanguageStandaloneSetup;
import com.shs.hl.hearingLanguage.AssignmentStatement;
import com.shs.hl.hearingLanguage.LocalVariableDeclaration;
import com.shs.hl.tests.R;

import de.itemis.xtext.typesystem.testing.XTextTestCase;

public class Text extends XTextTestCase {

	@Test
	public void testIssues() throws Exception {
		initializeAndGetRoot(new HearingLanguageStandaloneSetup(), 
				R.modelroot+"/text/text.hl");

		assertConstraints( allIssues.errorsOnly().sizeIs(3) );
		assertConstraints( allIssues.forType(LocalVariableDeclaration.class).named("t").theOneAndOnlyContains("incompatible") );
		assertConstraints( allIssues.forType(LocalVariableDeclaration.class).named("ss2").theOneAndOnlyContains("incompatible") );
		assertConstraints( allIssues.forType(AssignmentStatement.class).theOneAndOnlyContains("implicitly const") );
	}
	
	

}
