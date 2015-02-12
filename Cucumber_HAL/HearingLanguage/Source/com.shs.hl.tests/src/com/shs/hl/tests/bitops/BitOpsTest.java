package com.shs.hl.tests.bitops;

import org.junit.Test;

import com.shs.hl.HearingLanguageStandaloneSetup;
import com.shs.hl.hearingLanguage.BitAnd;
import com.shs.hl.hearingLanguage.BitCheck;
import com.shs.hl.hearingLanguage.BitComplementExpr;
import com.shs.hl.tests.R;

import de.itemis.xtext.typesystem.testing.XTextTestCase;

public class BitOpsTest extends XTextTestCase {

	@Test
	public void testTypesOfParams() throws Exception {
		initializeAndGetRoot(new HearingLanguageStandaloneSetup(), 
				R.modelroot+"/bitops/bitops.hl");

		assertConstraints( allIssues.errorsOnly().sizeIs(4) );
		assertConstraints( allIssues.forType(BitComplementExpr.class).theOneAndOnlyContains("incompatible type") );
		assertConstraints( allIssues.forType(BitAnd.class).allOfThemContain("incompatible") );
	
		assertConstraints( allIssues.forType(BitCheck.class).theOneAndOnlyContains("incompatible type") );
	}
	
}
