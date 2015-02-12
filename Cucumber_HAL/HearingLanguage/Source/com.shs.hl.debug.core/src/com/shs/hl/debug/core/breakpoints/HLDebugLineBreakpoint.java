package com.shs.hl.debug.core.breakpoints;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.IThread;
import org.eclipse.debug.core.model.LineBreakpoint;

import com.shs.hl.debug.core.Constants;
import com.shs.hl.debug.core.model.HLDebugTarget;
import com.shs.hl.debug.core.model.IHLMacroEventListener;

public class HLDebugLineBreakpoint extends LineBreakpoint implements
		IHLMacroEventListener {

	// target currently installed in
	private HLDebugTarget fTarget;

	/**
	 * Default constructor is required for the breakpoint manager to re-create
	 * persisted breakpoints. After instantiating a breakpoint, the
	 * <code>setMarker(...)</code> method is called to restore this breakpoint's
	 * attributes.
	 */
	public HLDebugLineBreakpoint() {
	}

	/**
	 * Constructs a line breakpoint on the given resource at the given line
	 * number. The line number is 1-based (i.e. the first line of a file is line
	 * number 1).
	 * 
	 * @param resource
	 *            file on which to set the breakpoint
	 * @param lineNumber
	 *            1-based line number of the breakpoint
	 * @param content
	 *            content of lineNumber
	 * @throws CoreException
	 *             if unable to create the breakpoint
	 */
	public HLDebugLineBreakpoint(final IResource resource,
			final int lineNumber, final String contentOfLine)
			throws CoreException {
		IWorkspaceRunnable runnable = new IWorkspaceRunnable() {
			@Override
			public void run(IProgressMonitor monitor) throws CoreException {
				IMarker marker = resource.createMarker(Constants.HL_MARKER_ID);
				setMarker(marker);
				marker.setAttribute(IBreakpoint.ENABLED, Boolean.TRUE);
				marker.setAttribute(IMarker.LINE_NUMBER, lineNumber);
				marker.setAttribute(IBreakpoint.ID, getModelIdentifier());
				marker.setAttribute(IMarker.MESSAGE, "Line Breakpoint: "
						+ resource.getName() + " [line: " + lineNumber + "]");
				marker.setAttribute("CONTENT", contentOfLine);
			}
		};
		run(getMarkerRule(resource), runnable);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.debug.core.model.IBreakpoint#getModelIdentifier()
	 */
	@Override
	public String getModelIdentifier() {
		return Constants.ID_HL_DEBUG_MODEL;
	}

	/**
	 * Returns whether this breakpoint is a run-to-line breakpoint
	 * 
	 * @return whether this breakpoint is a run-to-line breakpoint
	 */
	public boolean isRunToLineBreakpoint() {
		return false;
	}

	/**
	 * Installs this breakpoint in the given c# remote component. Registeres
	 * this breakpoint as an event listener in the given target and creates the
	 * breakpoint specific request.
	 * 
	 * @param target
	 *            C# remote component
	 * @throws CoreException
	 *             if installation fails
	 */
	public void install(HLDebugTarget target) throws CoreException {
		fTarget = target;
		// target.addEventListener(this);
		createRequest(target);
	}

	/**
	 * Create the breakpoint specific request in the target. Subclasses should
	 * override.
	 * 
	 * @param target
	 *            C# remote component
	 * @throws CoreException
	 *             if request creation fails
	 */

	protected void createRequest(HLDebugTarget target) throws CoreException {
		target.sendBreakPointRequest(this);

	}

	/**
	 * Removes this breakpoint's event request from the target. Subclasses
	 * should override.
	 * 
	 * @param target
	 *            C# Debugger
	 * 
	 *            * @throws CoreException if clearing the request fails
	 */
	// TODO remove the content elements from request
	protected void clearRequest(HLDebugTarget target) throws CoreException {
		// TODO
		// .sendRequest("clear " + (getLineNumber() - 1) + " content: " +
		// getMarker().getAttribute("CONTENT"));
	}

	/**
	 * Removes this breakpoint from the given interprettor. Removes this
	 * breakpoint as an event listener and clears the request for the
	 * interprettor.
	 * 
	 * @param target
	 *            C# runtime
	 * @throws CoreException
	 *             if removal fails
	 */
	public void remove(HLDebugTarget target) throws CoreException {
		// target.removeEventListener(this);
		clearRequest(target);
		fTarget = null;

	}

	/**
	 * Returns the target this breakpoint is installed in or <code>null</code>.
	 * 
	 * @return the target this breakpoint is installed in or <code>null</code>
	 */
	protected HLDebugTarget getDebugTarget() {
		return fTarget;
	}

	/**
	 * Notify's the HL debug server that this breakpoint has been hit.
	 */
	protected void notifyThread() {
		if (fTarget != null) {
			try {
				IThread[] threads = fTarget.getThreads();
				if (threads.length == 1) {

					// HLDebugThread thread = (HLDebugThread) threads[0];
					// TODO
					// thread.suspendedBy(this);
				}
			} catch (DebugException e) {
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * Subclasses should override to handle their breakpoint specific event.
	 * 
	 * @see
	 * example.debug.core.model.IPDAEventListener#handleEvent(java.lang.String)
	 */
	@Override
	public void handleEvent(String event) {
		if (event.startsWith("suspended breakpoint")) {
			handleHit(event);
		}
	}

	/**
	 * Determines if this breakpoint was hit and notifies the thread.
	 * 
	 * @param event
	 *            breakpoint event
	 */
	private void handleHit(String event) {
		int lastSpace = event.lastIndexOf(' ');
		if (lastSpace > 0) {
			String line = event.substring(lastSpace + 1);
			int lineNumber = Integer.parseInt(line);
			// breakpoints event line numbers are 0 based, model objects are 1
			// based
			lineNumber++;
			try {
				if (getLineNumber() == lineNumber) {
					notifyThread();
				}
			} catch (CoreException e) {
			}
		}
	}

}
