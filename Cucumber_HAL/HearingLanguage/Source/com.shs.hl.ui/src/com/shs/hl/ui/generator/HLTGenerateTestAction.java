package com.shs.hl.ui.generator;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;

public class HLTGenerateTestAction
{

	private static final String console_name = "com.shs.hl.generator.console";

	private final HLTTestGenerator gen;

	public HLTGenerateTestAction()
	{
		// gen = new StaticAnalyzer(console_name);
		gen = new HLTTestGenerator(console_name);
	}

	public void run(ISelection selection)
	{
		if (selection != null && !selection.isEmpty() && selection instanceof IStructuredSelection)
		{
			gen.generateTest(selection);

		}
	}
}
