package com.shs.hl.tests.bitops;

import org.junit.Test;

import com.shs.hl.HearingLanguageStandaloneSetup;
import com.shs.hl.tests.R;

import de.itemis.xtext.typesystem.testing.XTextTestCase;

public class Bitmasks extends XTextTestCase {

	@Test
	public void testTypesOfParams() throws Exception {
		initializeAndGetRoot(new HearingLanguageStandaloneSetup(), 
				R.modelroot+"/bitops/bitmasks.hl",
				R.allParameterFilesAndBuiltIns);

		assertConstraints( allIssues.errorsOnly().sizeIs(0) );
	
	}
	
}
