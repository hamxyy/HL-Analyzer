package com.shs.hl.tests.curves;

import org.junit.Test;

import com.shs.hl.HearingLanguageStandaloneSetup;
import com.shs.hl.tests.R;

import de.itemis.xtext.typesystem.testing.XTextTestCase;

public class LevelCurves extends XTextTestCase {

	@Test
	public void testTypesOfParams() throws Exception {
		initializeAndGetRoot(new HearingLanguageStandaloneSetup(), 
				R.modelroot+"/curves/levelcurves.hl",
				R.allParameterFilesAndBuiltIns);
	
		assertConstraints( allIssues.errorsOnly().sizeIs(0) );
		
		allIssues.dumpIssues();
	}
	
}
