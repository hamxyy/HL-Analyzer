package com.shs.hl.tests.functionattrs;

import org.junit.Test;

import com.shs.hl.HearingLanguageStandaloneSetup;
import com.shs.hl.tests.R;

import de.itemis.xtext.typesystem.testing.XTextTestCase;

public class FunctionAttrs extends XTextTestCase {

	@Test
	public void testTypesOfParams() throws Exception {
		initializeAndGetRoot(new HearingLanguageStandaloneSetup(), 
				R.modelroot+"/functionattrs/functionattrs.hl");

	
		assertConstraints( allIssues.errorsOnly().sizeIs(0) );

		
	}
	
}
