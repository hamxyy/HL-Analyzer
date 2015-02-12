package com.shs.hl.tests.namespaces;

import org.junit.Test;

import com.shs.hl.HearingLanguageStandaloneSetup;
import com.shs.hl.hearingLanguage.FunctionDeclaration;
import com.shs.hl.hearingLanguage.SymbolReference;
import com.shs.hl.tests.R;

import de.itemis.xtext.typesystem.testing.XTextTestCase;

public class Namespaces extends XTextTestCase {

	@Test
	public void testTypesOfParams() throws Exception {
		initializeAndGetRoot(new HearingLanguageStandaloneSetup(), 
				R.modelroot+"/namespaces/n1.hl",
				R.modelroot+"/namespaces/n2.hl",
				R.modelroot+"/namespaces/n3.hl");

	
		allIssues.dumpIssues();
		assertConstraints( allIssues.errorsOnly().sizeIs(7) );

		assertConstraints( allIssues.forType(SymbolReference.class).under( FunctionDeclaration.class, "n1f2").get(0).theOneAndOnlyContains("resolve reference") );
		assertConstraints( allIssues.forType(SymbolReference.class).under( FunctionDeclaration.class, "n1f2").get(1).theOneAndOnlyContains("cannot have arguments") );
		
		assertConstraints( allIssues.forType(SymbolReference.class).under( FunctionDeclaration.class, "n1f3").get(0).theOneAndOnlyContains("protected functions cannot") );

		assertConstraints( allIssues.forType(SymbolReference.class).under( FunctionDeclaration.class, "n1f1").nOfThemContain(2, "resolve reference") );
		assertConstraints( allIssues.forType(SymbolReference.class).under( FunctionDeclaration.class, "n1f1").nOfThemContain(2, "cannot have arguments") );

		
	}
	
}
