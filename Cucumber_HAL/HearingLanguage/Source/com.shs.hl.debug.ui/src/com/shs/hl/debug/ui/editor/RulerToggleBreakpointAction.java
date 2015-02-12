package com.shs.hl.debug.ui.editor;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.text.source.IVerticalRulerInfo;
import org.eclipse.ui.texteditor.AbstractRulerActionDelegate;
import org.eclipse.ui.texteditor.ITextEditor;

public class RulerToggleBreakpointAction extends AbstractRulerActionDelegate {

    @Override
    protected IAction createAction(ITextEditor editor,
	    IVerticalRulerInfo rulerInfo) {
	return new ToggleBreakpointAction(editor, rulerInfo);
    }

}
