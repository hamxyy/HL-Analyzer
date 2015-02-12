package com.shs.hl.tests.packag;

import org.junit.Test;

import com.shs.hl.HearingLanguageStandaloneSetup;
import com.shs.hl.hearingLanguage.FunctionDeclaration;
import com.shs.hl.hearingLanguage.SymbolReference;
import com.shs.hl.tests.R;

import de.itemis.xtext.typesystem.testing.XTextTestCase;

public class Packag extends XTextTestCase {

	@Test
	public void testTypesOfParams() throws Exception {
		initializeAndGetRoot(new HearingLanguageStandaloneSetup(), 
				R.modelroot+"/packag/packag.hl");

	
		allIssues.dumpIssues();

		assertConstraints( allIssues.errorsOnly().sizeIs(2) );
		assertConstraints( allIssues.under(FunctionDeclaration.class,"f2").forType(SymbolReference.class).get(0).theOneAndOnlyContains("Couldn't resolve reference") );
		assertConstraints( allIssues.under(FunctionDeclaration.class,"f2").forType(SymbolReference.class).get(1).theOneAndOnlyContains("parameter references cannot have arguments") );
	}
	
}
