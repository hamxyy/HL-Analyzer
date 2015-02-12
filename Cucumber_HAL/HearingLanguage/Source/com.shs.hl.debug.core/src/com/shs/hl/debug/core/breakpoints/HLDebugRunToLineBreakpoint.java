package com.shs.hl.debug.core.breakpoints;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IBreakpoint;

import com.shs.hl.debug.core.Constants;

public class HLDebugRunToLineBreakpoint extends HLDebugLineBreakpoint {
	private IFile fSourceFile;
	
	public HLDebugRunToLineBreakpoint(final IFile resource, final int lineNumber) throws DebugException {
		IWorkspaceRunnable runnable = new IWorkspaceRunnable() {
			public void run(IProgressMonitor monitor) throws CoreException {
				IMarker marker = ResourcesPlugin.getWorkspace().getRoot().createMarker(Constants.HL_MARKER_ID);
				setMarker(marker);
				marker.setAttribute(IBreakpoint.ENABLED, Boolean.TRUE);
				marker.setAttribute(IMarker.LINE_NUMBER, lineNumber);
				marker.setAttribute(IBreakpoint.ID, getModelIdentifier());
				setRegistered(false);
				fSourceFile = resource;
			}
		};
		run(getMarkerRule(resource), runnable);		
	}
	
	public IFile getSourceFile() {
		return fSourceFile;
	}
	
	
}



