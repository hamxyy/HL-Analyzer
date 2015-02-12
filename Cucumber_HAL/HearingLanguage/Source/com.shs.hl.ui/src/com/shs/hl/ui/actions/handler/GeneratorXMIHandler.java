package com.shs.hl.ui.actions.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.handlers.HandlerUtil;

import com.shs.hl.ui.generator.XMIGeneratorAction;

public class GeneratorXMIHandler  extends AbstractHandler{

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ISelection selected  = HandlerUtil
				.getActiveWorkbenchWindow(event).getActivePage().getSelection();
		new XMIGeneratorAction().run(selected);
		return null;
	}

}
