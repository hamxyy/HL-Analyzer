package com.shs.hl.ui.generator;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.console.MessageConsoleStream;

import com.shs.hl.ui.utils.LoggerUtil;

public class TestRunnerAction implements IObjectActionDelegate {

	private ISelection				selection;
	private final TestRunner		runner;
	private final String			console_name			= "com.shs.hl.testrunnerconsole";
	protected MessageConsoleStream	messageConsoleStream	= LoggerUtil.findConsole(console_name).newMessageStream();

	public TestRunnerAction() {
		runner = new TestRunner(console_name);
	}

	@Override
	public void run(final IAction action) {
		if (selection != null && !selection.isEmpty() && selection instanceof IStructuredSelection) {
			runner.runGeneration1(selection);
		}
	}

	@Override
	public void selectionChanged(final IAction action, final ISelection fselection) {
		selection = fselection;
	}

	@Override
	public void setActivePart(final IAction action, final IWorkbenchPart targetPart) {

	}

}
