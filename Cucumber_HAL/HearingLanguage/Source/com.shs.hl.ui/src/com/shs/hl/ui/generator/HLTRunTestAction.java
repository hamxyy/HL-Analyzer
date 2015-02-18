package com.shs.hl.ui.generator;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;

public class HLTRunTestAction {

	private static final String console_name = "com.shs.hl.generator.console";

	private final HLTTestRunner gen;

	public HLTRunTestAction() {
//		gen = new StaticAnalyzer(console_name);
		gen = new HLTTestRunner(console_name);
	}

	public void run(ISelection selection) {
		if (selection != null && !selection.isEmpty()
				&& selection instanceof IStructuredSelection) {
			gen.runTest(selection);

		}
	}

}
