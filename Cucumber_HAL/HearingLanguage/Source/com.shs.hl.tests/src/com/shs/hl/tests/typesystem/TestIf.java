package com.shs.hl.tests.typesystem;

import org.junit.Test;

import com.shs.hl.HearingLanguageStandaloneSetup;
import com.shs.hl.hearingLanguage.ElseIf;
import com.shs.hl.hearingLanguage.IfStatement;
import com.shs.hl.tests.R;

import de.itemis.xtext.typesystem.testing.XTextTestCase;

public class TestIf extends XTextTestCase {

	@Test
	public void testIssues() throws Exception {
		initializeAndGetRoot(new HearingLanguageStandaloneSetup(), R.modelroot+"/typesystem/testif.hl");
		
		assertConstraints( allIssues.errorsOnly().sizeIs(2) );

		assertConstraints( allIssues.forType(IfStatement.class).sizeIs(1).oneOfThemContains("incompatible type") );
		assertConstraints( allIssues.forType(ElseIf.class).sizeIs(1).oneOfThemContains("incompatible type") );

		
	}
	
	

}
