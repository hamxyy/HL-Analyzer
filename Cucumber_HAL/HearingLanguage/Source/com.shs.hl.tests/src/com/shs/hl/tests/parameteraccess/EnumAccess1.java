package com.shs.hl.tests.parameteraccess;

import org.junit.Test;

import com.shs.hl.HearingLanguageStandaloneSetup;
import com.shs.hl.hearingLanguage.AssignmentStatement;
import com.shs.hl.hearingLanguage.DefinedExpression;
import com.shs.hl.hearingLanguage.ParameterReadExpression;
import com.shs.hl.tests.R;

import de.itemis.xtext.typesystem.testing.XTextTestCase;

public class EnumAccess1 extends XTextTestCase {

	@Test
	public void testTypesOfParams() throws Exception {
		initializeAndGetRoot(new HearingLanguageStandaloneSetup(), 
				R.modelroot+"/parameteraccess/enumaccess1.hl",
				R.allParameterFiles);

		allIssues.dumpIssues();

		assertConstraints( allIssues.errorsOnly().sizeIs(3) ); 

		assertConstraints( allIssues.forType(ParameterReadExpression.class).oneOfThemContains("Couldn't resolve reference") );
		assertConstraints( allIssues.forType(ParameterReadExpression.class).oneOfThemContains("cannot be assigned") );
		assertConstraints( allIssues.forType(AssignmentStatement.class).theOneAndOnlyContains("is undefined") );
		assertConstraints( allIssues.forType(DefinedExpression.class).theOneAndOnlyContains("can only be called") );

	}
	
}
