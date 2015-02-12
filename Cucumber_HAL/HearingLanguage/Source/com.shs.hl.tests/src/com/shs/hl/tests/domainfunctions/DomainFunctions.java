package com.shs.hl.tests.domainfunctions;

import org.junit.Test;

import com.shs.hl.HearingLanguageStandaloneSetup;
import com.shs.hl.hearingLanguage.FunctionDeclaration;
import com.shs.hl.hearingLanguage.SymbolReference;
import com.shs.hl.tests.R;

import de.itemis.xtext.typesystem.testing.XTextTestCase;

public class DomainFunctions extends XTextTestCase {

	@Test
	public void testTypesOfParams() throws Exception {
		initializeAndGetRoot(new HearingLanguageStandaloneSetup(), 
				R.modelroot+"/domainfunctions/domainfunctions.hl");

		assertConstraints( allIssues.errorsOnly().sizeIs(2) );
		assertConstraints( allIssues.forType(SymbolReference.class).under(FunctionDeclaration.class, "d1").theOneAndOnlyContains("protected functions cannot be called from outside their namespace") );
		assertConstraints( allIssues.forType(SymbolReference.class).under(FunctionDeclaration.class, "f11").theOneAndOnlyContains("protected functions cannot be called from outside their namespace") );

	}
	
}
