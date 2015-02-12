package com.shs.hl.tests.parameteraccess;

import org.junit.Test;

import com.shs.hl.HearingLanguageStandaloneSetup;
import com.shs.hl.hearingLanguage.FunctionDeclaration;
import com.shs.hl.hearingLanguage.OppositeExpression;
import com.shs.hl.tests.R;

import de.itemis.xtext.typesystem.testing.XTextTestCase;

public class Opposite extends XTextTestCase {

	@Test
	public void testTypesOfParams() throws Exception {
		initializeAndGetRoot(new HearingLanguageStandaloneSetup(), 
				R.modelroot+"/parameteraccess/opposite.hl", 
				R.allParameterFilesAndBuiltIns);
	
		allIssues.dumpIssues();
		assertConstraints( allIssues.errorsOnly().sizeIs(3) );

		assertConstraints( allIssues.forType(OppositeExpression.class).under(FunctionDeclaration.class, "f").oneOfThemContains("can only be used with") );
		assertConstraints( allIssues.forType(OppositeExpression.class).under(FunctionDeclaration.class, "f").oneOfThemContains("incompatible type") );
		assertConstraints( allIssues.forType(OppositeExpression.class).under(FunctionDeclaration.class, "more").theOneAndOnlyContains("incompatible type") );
		dumpUnassertedIssues();
		
	}
	
}
