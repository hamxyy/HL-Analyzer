package com.shs.hl.tests.typesystem;

import org.junit.Test;

import com.shs.hl.HearingLanguageStandaloneSetup;
import com.shs.hl.hearingLanguage.FunctionDeclaration;
import com.shs.hl.hearingLanguage.NumberLiteral;
import com.shs.hl.hearingLanguage.ParameterDeclaration;
//import com.shs.hl.hearingLanguage.Plus;
import com.shs.hl.hearingLanguage.ReturnStatement;
import com.shs.hl.hearingLanguage.SymbolReference;
import com.shs.hl.tests.R;

import de.itemis.xtext.typesystem.testing.XTextTestCase;

public class Test1 extends XTextTestCase {

	@Test
	public void testIssues() throws Exception {
		initializeAndGetRoot(new HearingLanguageStandaloneSetup(), 
				R.modelroot+"/typesystem/test1.hl");
		
		allIssues.dumpIssues();
		
		
		assertConstraints( allIssues.errorsOnly().sizeIs(11) );
		 // since not all deep issus might be collected and recognized at once (especially plus2) it is better to check such issues seperately
		//assertConstraints( allIssues.errorsOnly().sizeIs(10) );
		
		
		assertConstraints( allIssues.forType(SymbolReference.class).under(FunctionDeclaration.class, "plus2").sizeIs(1).theOneAndOnlyContains("wrong number"));
		assertConstraints( allIssues.forType(NumberLiteral.class).under(FunctionDeclaration.class, "plus2").sizeIs(1).theOneAndOnlyContains("incompatible type"));
		
		assertConstraints( allIssues.forType(ParameterDeclaration.class).under(FunctionDeclaration.class, "plus1").oneOfThemContains("parameters cannot be void") );
		assertConstraints( allIssues.forType(SymbolReference.class).under(FunctionDeclaration.class, "plus1").sizeIs(2).allOfThemContain("incompatible type"));
		

		assertConstraints( allIssues.forType(ReturnStatement.class).under(FunctionDeclaration.class, "f3").theOneAndOnlyContains("incompatible type") );
		

		assertConstraints(allIssues.under(FunctionDeclaration.class,"f4").sizeIs(3).oneOfThemContains("names are defined multiple times"));
		assertConstraints( allIssues.forType(NumberLiteral.class).under(FunctionDeclaration.class, "f4").theOneAndOnlyContains("incompatible") );
		assertConstraints( allIssues.forType(ReturnStatement.class).under(FunctionDeclaration.class, "f4").theOneAndOnlyContains("incompatible") );
		
		dumpUnassertedIssues();
		
	}
	
	

}

