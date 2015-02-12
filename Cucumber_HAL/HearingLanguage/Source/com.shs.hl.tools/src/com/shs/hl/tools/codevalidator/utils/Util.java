package com.shs.hl.tools.codevalidator.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Util {

	final static String	OUTDIR	= "html";

	public static String getLines(File file) {
		String linesepar = System.getProperty("line.separator");

		StringBuilder builder = new StringBuilder();
		BufferedReader in=null;
		try {
			in = new BufferedReader(new FileReader(file));
			String line;
			while ((line = in.readLine()) != null) {
				builder.append(line + linesepar);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally{
			if (in!=null)
			{
				try{
					in.close();
				}
				catch(Exception e)
				{
					
				}
			}
		}
		// remove last line break before returning
		return builder.substring(0, builder.lastIndexOf(linesepar));
	}

	public static void cleanOutDir() {
		File file = new File(OUTDIR);
		if (file.exists()) {
			file.delete();
			file.mkdir();
		}
	}

	public static void writeFile(String fileName, String content)
			throws IOException {
		File file = new File(OUTDIR + "/" + fileName);
		if (!file.exists()) {
			file.createNewFile();
		}
		FileWriter fstream = new FileWriter(file);
		BufferedWriter out = new BufferedWriter(fstream);
		out.write(content);
		out.close();
	}
}
