package com.shs.hl.tests.typesystem;

import org.junit.Test;

import com.shs.hl.HearingLanguageStandaloneSetup;
import com.shs.hl.hearingLanguage.Case;
import com.shs.hl.tests.R;

import de.itemis.xtext.typesystem.testing.XTextTestCase;

public class Switch extends XTextTestCase {

	@Test
	public void testIssues() throws Exception {
		initializeAndGetRoot(new HearingLanguageStandaloneSetup(), 
				R.modelroot+"/typesystem/switch.hl",
				R.allParameterFiles);

		allIssues.dumpIssues();
		dumpUnassertedIssues();
		
		assertConstraints( allIssues.errorsOnly().sizeIs(1) );
		assertConstraints( allIssues.forType(Case.class).sizeIs(1).oneOfThemContains("incompatible type") );

	
		
	}
	
	

}
