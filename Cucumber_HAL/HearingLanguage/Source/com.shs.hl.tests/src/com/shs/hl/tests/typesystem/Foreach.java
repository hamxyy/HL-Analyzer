package com.shs.hl.tests.typesystem;

import org.junit.Test;

import com.shs.hl.HearingLanguageStandaloneSetup;
import com.shs.hl.hearingLanguage.BreakStatement;
import com.shs.hl.hearingLanguage.ForeachStatement;
import com.shs.hl.hearingLanguage.FunctionDeclaration;
import com.shs.hl.tests.R;

import de.itemis.xtext.typesystem.testing.XTextTestCase;

public class Foreach extends XTextTestCase {

	@Test
	public void testIssues() throws Exception {
		initializeAndGetRoot(new HearingLanguageStandaloneSetup(), 
				R.modelroot+"/typesystem/foreach.hl");

		allIssues.dumpIssues();
		assertConstraints( allIssues.errorsOnly().sizeIs(3) );
		
		assertConstraints( allIssues.forType(ForeachStatement.class).oneOfThemContains("iterable, but found int") );
		assertConstraints( allIssues.forType(ForeachStatement.class).oneOfThemContains("channels requires channel") );
		assertConstraints( allIssues.forType(BreakStatement.class).under(FunctionDeclaration.class, "g").theOneAndOnlyContains("break must be inside a for") );
		
	}
	
	

}
