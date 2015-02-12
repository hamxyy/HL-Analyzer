package com.shs.hl.postprocessing;

import java.util.Map.Entry;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;

import com.shs.hl.postprocessing.grammar.csharppostprocessorLexer;
import com.shs.hl.postprocessing.grammar.csharppostprocessorParser;

public class Main {

	public static void main(String[] args) {
		String teststrin = "/*#START#1037477562#19#2SHS.SAT.FittingHIDBInterfaces.HearingLanguageRuntime.shs_sat.BasicTuning_SoftSoundsDownBu::IsEnabled#*/\n";
		ANTLRStringStream in = new ANTLRStringStream(teststrin);
		csharppostprocessorLexer lex = new csharppostprocessorLexer(in);
		CommonTokenStream tokens2 = new CommonTokenStream(lex);
		csharppostprocessorParser pars = new csharppostprocessorParser(tokens2);
		try {
			pars.line();
			for (Entry<String, StringBuilder> entry : lex.getMap().entrySet()) {
				System.out.println(entry.getValue());
			}
		} catch (RecognitionException e) {
			e.printStackTrace();
		}
	}
}
