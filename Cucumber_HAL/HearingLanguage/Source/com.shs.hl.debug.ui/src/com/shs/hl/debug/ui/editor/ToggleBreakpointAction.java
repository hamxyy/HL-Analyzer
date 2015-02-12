package com.shs.hl.debug.ui.editor;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.text.TextSelection;
import org.eclipse.jface.text.source.IVerticalRulerInfo;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.texteditor.ITextEditor;

public class ToggleBreakpointAction extends Action {

    private final ITextEditor fEditor;
    private final IVerticalRulerInfo fRulerInfo;

    public ToggleBreakpointAction(ITextEditor editor,
	    IVerticalRulerInfo rulerInfo) {
	this.fEditor = editor;
	this.fRulerInfo = rulerInfo;
    }

    @Override
    public void run() {
	HLBreakpointAdapter adapter = new HLBreakpointAdapter();
	int line = fRulerInfo.getLineOfLastMouseButtonActivity();
	IDocumentProvider provider = fEditor.getDocumentProvider();
	ITextSelection selection = null;
	try {
	    provider.connect(fEditor.getEditorInput());
	    IDocument document = provider.getDocument(fEditor.getEditorInput());
	    IRegion region = document.getLineInformation(line);
	    selection = new TextSelection(document, region.getOffset(),
		    region.getLength());
	} catch (CoreException e1) {
	} catch (BadLocationException e) {
	} finally {
	    provider.disconnect(fEditor.getEditorInput());
	}
	if (selection != null) {
	    try {
		if (adapter.canToggleWatchpoints(fEditor, selection)) {
		    adapter.toggleWatchpoints(fEditor, selection);
		} else if (adapter.canToggleLineBreakpoints(fEditor, selection)) {
		    adapter.toggleLineBreakpoints(fEditor, selection);
		}
	    } catch (CoreException e) {
	    }
	 }
    }

}
