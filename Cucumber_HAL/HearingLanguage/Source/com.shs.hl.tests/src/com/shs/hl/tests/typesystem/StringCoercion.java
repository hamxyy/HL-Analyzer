package com.shs.hl.tests.typesystem;

import org.junit.Test;

import com.shs.hl.HearingLanguageStandaloneSetup;
import com.shs.hl.hearingLanguage.LocalVariableDeclaration;
import com.shs.hl.tests.R;

import de.itemis.xtext.typesystem.testing.XTextTestCase;

public class StringCoercion extends XTextTestCase {

	@Test
	public void testIssues() throws Exception {
		initializeAndGetRoot(new HearingLanguageStandaloneSetup(), 
				R.modelroot+"/typesystem/stringcoercion.hl");

		assertConstraints( allIssues.errorsOnly().sizeIs(1) );
		assertConstraints( allIssues.forType(LocalVariableDeclaration.class).theOneAndOnlyContains("incompatible type") );
		
	}
	
	

}
