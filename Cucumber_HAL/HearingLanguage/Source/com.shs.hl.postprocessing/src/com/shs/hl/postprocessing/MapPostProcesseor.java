package com.shs.hl.postprocessing;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.eclipse.xtend2.lib.StringConcatenation;

import com.shs.hl.postprocessing.grammar.csharppostprocessorLexer;
import com.shs.hl.postprocessing.grammar.csharppostprocessorParser;

public class MapPostProcesseor {
	private final InputStream	fileInputStream;
	private final String		fileName;

	public MapPostProcesseor(String name, InputStream inputStream) {
		this.fileInputStream = inputStream;
		this.fileName = name;

	}

	public CharSequence getContents() {
		StringConcatenation builder = new StringConcatenation();
		Map<String, StringBuilder> map = new HashMap<String, StringBuilder>();
		String hlFile = "";
		List<String> hashList = new ArrayList<String>();
		try {
			ANTLRInputStream in = new ANTLRInputStream(fileInputStream);
			csharppostprocessorLexer lex = new csharppostprocessorLexer(in);
			CommonTokenStream tokens2 = new CommonTokenStream(lex);
			csharppostprocessorParser pars = new csharppostprocessorParser(tokens2);
			pars.line();
			map = lex.getMap();
			hlFile = lex.getFileName();
			this.fileInputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (RecognitionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (Entry<String, StringBuilder> entry : map.entrySet()) {
			builder.append(entry.getValue() + "|" + hlFile + "|" + fileName + "\n");
			hashList.add(entry.getKey());
		}
		// remove if hashs are the same if the generated
		writeHashList(hashList);
		return builder;
	}

	// this is only for verification that the hashs are the same everytime we
	// generate the hashs!!!
	private void writeHashList(List<String> hashList) {
		StringBuilder builder = new StringBuilder();
		for (String str : hashList) {
			builder.append(str + "\n");
		}
		FileWriter fstream;
		try {
			fstream = new FileWriter("hash" + ".txt");
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(builder.toString());
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
