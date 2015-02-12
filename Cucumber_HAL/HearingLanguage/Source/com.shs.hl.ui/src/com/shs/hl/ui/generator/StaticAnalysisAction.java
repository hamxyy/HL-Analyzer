package com.shs.hl.ui.generator;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;

public class StaticAnalysisAction {

	private static final String console_name = "com.shs.hl.generator.console";

	private final HLTRunner gen;

	public StaticAnalysisAction() {
//		gen = new StaticAnalyzer(console_name);
		gen = new HLTRunner(console_name);
	}

	public void run(ISelection selection) {
		if (selection != null && !selection.isEmpty()
				&& selection instanceof IStructuredSelection) {
			gen.runTest(selection);

		}
	}

}
