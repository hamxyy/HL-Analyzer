package com.shs.hl.debug.ui.editor;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.ILineBreakpoint;
import org.eclipse.debug.ui.actions.IToggleBreakpointsTarget;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.texteditor.ITextEditor;

import com.shs.hl.debug.core.breakpoints.HLDebugLineBreakpoint;
import com.shs.hl.debug.ui.Constants;

public class HLBreakpointAdapter implements IToggleBreakpointsTarget {

	@Override
	public void toggleLineBreakpoints(IWorkbenchPart part, ISelection selection)
			throws CoreException {
		ITextEditor textEditor = getEditor(part);
		if (textEditor != null) {
			IResource resource = (IResource) textEditor.getEditorInput()
					.getAdapter(IResource.class);
			ITextSelection textSelection = (ITextSelection) selection;
			int lineNumber = textSelection.getStartLine();
			String content = textSelection.getText();
			if (!isCommentLine(content)) {
				IBreakpoint[] breakpoints = DebugPlugin.getDefault()
						.getBreakpointManager()
						.getBreakpoints(Constants.ID_HL_DEBUG_MODEL);
				for (int i = 0; i < breakpoints.length; i++) {
					IBreakpoint breakpoint = breakpoints[i];
					if (breakpoint instanceof ILineBreakpoint
							&& resource.equals(breakpoint.getMarker()
									.getResource())) {
						if (((ILineBreakpoint) breakpoint).getLineNumber() == (lineNumber + 1)) {
							// remove
							breakpoint.delete();
							return;
						}
					}
				}
				// create line breakpoint (doc line numbers start at 0)
				HLDebugLineBreakpoint linebreakpoint = new HLDebugLineBreakpoint(
						resource, lineNumber + 1, content);
				DebugPlugin.getDefault().getBreakpointManager()
						.addBreakpoint(linebreakpoint);
			}
		}
	}

	private boolean isCommentLine(String str) {
		return str.trim().startsWith("//");
	}

	private ITextEditor getEditor(IWorkbenchPart part) {
		if (part instanceof TextEditor) {
			TextEditor editorPart = (TextEditor) part;
			IResource resource = (IResource) editorPart.getEditorInput()
					.getAdapter(IResource.class);
			if (resource != null) {
				String extension = resource.getFileExtension();
				if (extension != null && extension.equals("hl")) {
					return editorPart;
				}
			}
		}
		return null;
	}

	@Override
	public boolean canToggleLineBreakpoints(IWorkbenchPart part,
			ISelection selection) {
		return true;
	}

	@Override
	public void toggleMethodBreakpoints(IWorkbenchPart part,
			ISelection selection) throws CoreException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean canToggleMethodBreakpoints(IWorkbenchPart part,
			ISelection selection) {
		return true;
	}

	@Override
	public void toggleWatchpoints(IWorkbenchPart part, ISelection selection)
			throws CoreException {
		// TODO Auto-generated method stub
		toggleLineBreakpoints(part, selection);
	}

	@Override
	public boolean canToggleWatchpoints(IWorkbenchPart part,
			ISelection selection) {
		return true;
	}

}
