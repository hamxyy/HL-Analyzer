package com.shs.hl.tests.typesystem;

import org.junit.Test;

import com.shs.hl.HearingLanguageStandaloneSetup;
import com.shs.hl.hearingLanguage.LocalVariableDeclaration;
import com.shs.hl.tests.R;

import de.itemis.xtext.typesystem.testing.XTextTestCase;

public class Subtyping extends XTextTestCase {

	@Test
	public void testIssues() throws Exception {
		initializeAndGetRoot(new HearingLanguageStandaloneSetup(), R.modelroot+"/typesystem/subtyping.hl");
		assertConstraints( allIssues.errorsOnly().sizeIs(2) );

		assertConstraints( allIssues.forType(LocalVariableDeclaration.class).sizeIs(2).oneOfThemContains("incompatible type") );
	}
	
	

}
