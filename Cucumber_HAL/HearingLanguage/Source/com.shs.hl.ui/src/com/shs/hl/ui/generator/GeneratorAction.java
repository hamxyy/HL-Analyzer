package com.shs.hl.ui.generator;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;

/**
 * This class is used if you select Generate C# files on a folder, project, or
 * file. It generates the C# code from the hearing language files without
 * generating a dll file.
 * 
 */
public class GeneratorAction {

	private static final String console_name = "com.shs.hl.generator.console";

	private final CSharpGenerator gen;

	public GeneratorAction() {
		gen = new CSharpGenerator(console_name);
	}

	public void run(ISelection selection) {
		if (selection != null && !selection.isEmpty()
				&& selection instanceof IStructuredSelection) {
			gen.startUpGeneration(selection);

		}
	}

}
