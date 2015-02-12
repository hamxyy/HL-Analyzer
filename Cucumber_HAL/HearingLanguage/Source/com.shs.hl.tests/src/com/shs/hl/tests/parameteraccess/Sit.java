package com.shs.hl.tests.parameteraccess;

import org.junit.Test;

import com.shs.hl.HearingLanguageStandaloneSetup;
import com.shs.hl.hearingLanguage.SitExpression;
import com.shs.hl.tests.R;

import de.itemis.xtext.typesystem.testing.XTextTestCase;

public class Sit extends XTextTestCase {

	@Test
	public void testTypesOfParams() throws Exception {
		initializeAndGetRoot(new HearingLanguageStandaloneSetup(), 
				R.modelroot+"/parameteraccess/sit.hl",
				R.allParameterFiles);

		allIssues.dumpIssues();
		assertConstraints( allIssues.errorsOnly().sizeIs(4) ); 

		assertConstraints( allIssues.forType(SitExpression.class).oneOfThemContains("only int indexes") );
		assertConstraints( allIssues.forType(SitExpression.class).oneOfThemContains("only parameter access") );
		assertConstraints( allIssues.forType(SitExpression.class).oneOfThemContains("only hi parameter") );
		dumpUnassertedIssues();
	}
	
}
