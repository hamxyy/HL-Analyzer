package com.shs.hl.postprocessing;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map.Entry;

import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.eclipse.emf.mwe2.runtime.workflow.IWorkflowComponent;
import org.eclipse.emf.mwe2.runtime.workflow.IWorkflowContext;

import com.shs.hl.postprocessing.grammar.csharppostprocessorLexer;
import com.shs.hl.postprocessing.grammar.csharppostprocessorParser;

public class PostProcessingComponent implements IWorkflowComponent {

	String			input;
	StringBuilder	builder;
	String			mappingFileName;

	@Override
	public void preInvoke() {

	}

	@Override
	public void invoke(IWorkflowContext ctx) {
		builder = new StringBuilder();
		File dir = new File(input);
		if (!dir.exists()) {
			throw new IllegalStateException("no c# source folder specified");
		}
		for (File fl : dir.listFiles()) {
			if (fl.getName().endsWith(".cs"))
				parseFile(fl);
		}

	}

	private void parseFile(File file) {

		try {
			ANTLRFileStream in = new ANTLRFileStream(file.getAbsolutePath());
			csharppostprocessorLexer lex = new csharppostprocessorLexer(in);
			CommonTokenStream tokens2 = new CommonTokenStream(lex);
			csharppostprocessorParser pars = new csharppostprocessorParser(tokens2);
			pars.line();
			for (Entry<String, StringBuilder> entry : lex.getMap().entrySet()) {
				builder.append(entry.getValue() + "|" + lex.getFileName() + "|" + file.getName() + "\n");
			}
			writeFile();
		} catch (RecognitionException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void writeFile() {
		FileWriter fstream;
		try {
			File map = new File(mappingFileName);
			if (!map.exists()) {
				if (map.createNewFile()) {
					throw new IllegalStateException("Failed to create mapping file.");
				}
			}
			fstream = new FileWriter(mappingFileName);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(builder.toString());
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void postInvoke() {

	}

	public void addMappingFileName(String fileName) {
		this.mappingFileName = fileName;
	}

	public void addInput(String string) {
		this.input = string;
	}
}
