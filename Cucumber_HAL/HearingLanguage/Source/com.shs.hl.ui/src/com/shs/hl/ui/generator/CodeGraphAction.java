package com.shs.hl.ui.generator;

import org.eclipse.jface.viewers.ISelection;

public class CodeGraphAction {

	private CodeGraphGenerator graphGen;

	public void run(ISelection selection) {
		graphGen = new CodeGraphGenerator("com.shs.hl.codegraph");
		graphGen.startUpGeneration(selection);
	}

}
