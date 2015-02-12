package com.shs.hl.tests.arrays;

import org.junit.Test;

import com.shs.hl.HearingLanguageStandaloneSetup;
import com.shs.hl.hearingLanguage.ArrayAccessExpression;
import com.shs.hl.hearingLanguage.AssignmentStatement;
import com.shs.hl.hearingLanguage.FunctionDeclaration;
import com.shs.hl.tests.R;

import de.itemis.xtext.typesystem.testing.XTextTestCase;

public class ArrayTest1 extends XTextTestCase {

	@Test
	public void testTypesOfParams() throws Exception {
		initializeAndGetRoot(new HearingLanguageStandaloneSetup(), 
				R.modelroot+"/arrays/arraytest1.hl", 
				R.allParameterFiles);

		assertConstraints( "count", allIssues.errorsOnly().sizeIs(3) );
		
		allIssues.dumpIssues();
		
		assertConstraints( allIssues.forType(AssignmentStatement.class).under(FunctionDeclaration.class, "f1").theOneAndOnlyContains("incompatible type ArrayType") );
		assertConstraints( allIssues.forType(ArrayAccessExpression.class).under(FunctionDeclaration.class, "f2").theOneAndOnlyContains("incompatible type") );
		assertConstraints( allIssues.forType(ArrayAccessExpression.class).under(FunctionDeclaration.class, "f3").theOneAndOnlyContains("incompatible type") );
	
	}
	
}
