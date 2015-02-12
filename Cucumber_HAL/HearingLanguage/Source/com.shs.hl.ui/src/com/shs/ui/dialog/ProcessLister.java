package com.shs.ui.dialog;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ProcessLister {

	public static List<Process> getAllProcess() {
		List<Process> list = new ArrayList<Process>();
		Runtime runtime = Runtime.getRuntime();
		String cmds[] = { "cmd", "/c", "tasklist /fo csv", };
		try {
			java.lang.Process proc = runtime.exec(cmds);
			InputStream inputstream = proc.getInputStream();
			InputStreamReader inputstreamreader = new InputStreamReader(inputstream);
			BufferedReader bufferedreader = new BufferedReader(inputstreamreader);
			String line;
			bufferedreader.readLine();
			while ((line = bufferedreader.readLine()) != null) {
				String[] tokens = line.split(",");
				Process tmp = new Process(Integer.valueOf(cleanString(tokens[1])), cleanString(tokens[0]));
				list.add(tmp);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	private static String cleanString(String str) {
		return str.replaceAll("\"", "");
	}

	public static void main(String[] args) {
		getAllProcess();
	}
}
