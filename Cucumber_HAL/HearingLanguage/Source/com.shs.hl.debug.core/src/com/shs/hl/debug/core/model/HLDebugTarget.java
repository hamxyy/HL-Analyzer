package com.shs.hl.debug.core.model;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IMarkerDelta;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.IBreakpointManager;
import org.eclipse.debug.core.IBreakpointManagerListener;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.IMemoryBlock;
import org.eclipse.debug.core.model.IProcess;
import org.eclipse.debug.core.model.IThread;

import com.shs.hl.debug.core.breakpoints.HLDebugLineBreakpoint;
import com.shs.hl.debug.core.util.IdGenerator;
import com.shs.hl.net.DebugCommandNet;
import com.shs.hl.net.IDebugTCPClient;

public class HLDebugTarget extends HLDebugElement implements IDebugTarget,
		IBreakpointManagerListener {

	IProcess debugServerProcess;
	private final ILaunch launch;
	private IThread[] threads;
	private HLDebugThread thread;
	private final boolean fTerminated = false;

	public HLDebugTarget(final ILaunch flaunch, final IProcess fserver) {
		super(null);
		this.debugServerProcess = fserver;
		this.launch = flaunch;
		// BundleContext ctx = FrameworkUtil.getBundle(HLDebugTarget.class)
		// .getBundleContext();
		// ServiceReference<EventAdmin> ref =
		// ctx.getServiceReference(EventAdmin.class);
		init();

	}

	private void init() {
		thread = new HLDebugThread(this);
		threads = new IThread[] { thread };
		IBreakpointManager breakpointManager = getBreakpointManager();
		breakpointManager.addBreakpointListener(this);
		breakpointManager.addBreakpointManagerListener(this);

	}

	@Override
	public IDebugTarget getDebugTarget() {
		return this;
	}

	@Override
	public ILaunch getLaunch() {
		return launch;
	}

	@Override
	public boolean canTerminate() {
		// TODO
		return true; // debugServerProcess.canTerminate();
	}

	@Override
	public boolean isTerminated() {
		return fTerminated; // TODO || getProcess().isTerminated();
	}

	@Override
	public void terminate() throws DebugException {
		debugServerProcess.terminate();
	}

	@Override
	public boolean canResume() {
		return !isTerminated() && isSuspended();
	}

	@Override
	public boolean canSuspend() {
		return !isTerminated() && !isSuspended();
	}

	@Override
	public boolean isSuspended() {
		return !isTerminated() && getThread().isSuspended();
	}

	@Override
	public void resume() throws DebugException {
		getThread().resume();

	}

	@Override
	public void suspend() throws DebugException {
		getThread().resume();
	}

	@Override
	public void breakpointAdded(IBreakpoint breakpoint) {
		if (supportsBreakpoint(breakpoint)) {

			try {
				// TODO
				if ((breakpoint.isEnabled() && getBreakpointManager()
						.isEnabled()) || !breakpoint.isRegistered()) {
					HLDebugLineBreakpoint hlbreakpoint = (HLDebugLineBreakpoint) breakpoint;
					hlbreakpoint.install(this);
					DebugCommandNet command = getDebuggCommand(breakpoint,
							"add");
					sendCommand(command);

				}
			} catch (Exception e) {
				// TODO : implement robust exception handling
			}

		}

	}

	@Override
	public void breakpointRemoved(IBreakpoint breakpoint, IMarkerDelta delta) {
		if (supportsBreakpoint(breakpoint)) {
			try {
				((HLDebugLineBreakpoint) breakpoint).remove(this);
				DebugCommandNet command = getDebuggCommand(breakpoint, "delete");
				sendCommand(command);
			} catch (CoreException e) {
			}
		}

	}

	@Override
	public void breakpointChanged(IBreakpoint breakpoint, IMarkerDelta delta) {
		if (supportsBreakpoint(breakpoint)) {
			try {
				if (breakpoint.isEnabled()
						&& getBreakpointManager().isEnabled()) {
					breakpointAdded(breakpoint);
				} else {
					breakpointRemoved(breakpoint, null);
				}
			} catch (CoreException e) {
			}
		}
	}

	private DebugCommandNet getDebuggCommand(IBreakpoint point,
			String commandType) {

		try {
			String pathtoFile = point.getMarker().getResource().getName();
			Integer lineNumber = (Integer) point.getMarker().getAttribute(
					IMarker.LINE_NUMBER);
			DebugCommandNet command = new DebugCommandNet(
					IdGenerator.generateUniqueId(), "cmd", commandType,
					pathtoFile + "$" + lineNumber, "");
			return command;

		} catch (CoreException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean canDisconnect() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void disconnect() throws DebugException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isDisconnected() {
		return false;
	}

	@Override
	public boolean supportsStorageRetrieval() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public IMemoryBlock getMemoryBlock(long startAddress, long length)
			throws DebugException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IThread[] getThreads() throws DebugException {
		return threads;
	}

	@Override
	public boolean hasThreads() throws DebugException {
		return true;
	}

	@Override
	public String getName() throws DebugException {
		return "debug-session";
	}

	@Override
	public boolean supportsBreakpoint(IBreakpoint breakpoint) {
		// TODO : any checks?
		return true;
	}

	public synchronized HLDebugThread getThread() {
		return thread;
	}

	/**
	 * When the breakpoint manager disables, remove all registered breakpoints
	 * requests from the VM. When it enables, reinstall them.
	 */

	@Override
	public void breakpointManagerEnablementChanged(boolean enabled) {
		IBreakpoint[] breakpoints = getBreakpointManager().getBreakpoints(
				getModelIdentifier());
		for (int i = 0; i < breakpoints.length; i++) {
			if (enabled) {
				breakpointAdded(breakpoints[i]);
			} else {
				breakpointRemoved(breakpoints[i], null);
			}
		}

	}

	@Override
	public IProcess getProcess() {
		return debugServerProcess;
	}

	public void sendBreakPointRequest(
			HLDebugLineBreakpoint hlDebugLineBreakpoint) {
		DebugCommandNet command = getDebuggCommand(hlDebugLineBreakpoint, "add");
		sendCommand(command);
	}

	private void sendCommand(DebugCommandNet command) {
		((IDebugTCPClient) debugServerProcess).sendCommand(command);

	}
}
