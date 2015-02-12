package com.shs.hl.debug.core.model;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.IStackFrame;
import org.eclipse.debug.core.model.IThread;

import com.shs.hl.debug.core.breakpoints.HLDebugLineBreakpoint;

public class HLDebugThread extends HLDebugElement implements IThread {

	private boolean fStepping;

	public boolean isfSuspended() {
		return fSuspended;
	}

	public void setfSuspended(boolean fSuspended) {
		this.fSuspended = fSuspended;
	}

	public String getfErrorEvent() {
		return fErrorEvent;
	}

	public void setfErrorEvent(String fErrorEvent) {
		this.fErrorEvent = fErrorEvent;
	}

	public IBreakpoint getBreakpoint() {
		return breakpoint;
	}

	public void setBreakpoint(IBreakpoint breakpoint) {
		this.breakpoint = breakpoint;
	}

	public HLDebugLineBreakpoint getHlBreakpoint() {
		return hlBreakpoint;
	}

	public void setHlBreakpoint(HLDebugLineBreakpoint hlBreakpoint) {
		this.hlBreakpoint = hlBreakpoint;
	}

	private boolean fSuspended;
	private String fErrorEvent;
	private IBreakpoint breakpoint;
	private HLDebugLineBreakpoint hlBreakpoint;

	public HLDebugThread(HLDebugTarget target) {
		super(target);

	}

	@Override
	public boolean canResume() {
		return getDebugTarget().canResume();
	}

	@Override
	public boolean canSuspend() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSuspended() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void resume() throws DebugException {
		sendRequest("resume");

	}

	@Override
	public void suspend() throws DebugException {
		sendRequest("suspend");

	}

	@Override
	public boolean canStepInto() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canStepOver() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canStepReturn() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isStepping() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void stepInto() throws DebugException {
		// TODO Auto-generated method stub
		sendRequest("stepInto");
	}

	@Override
	public void stepOver() throws DebugException {
		sendRequest("stepOver");
	}

	@Override
	public void stepReturn() throws DebugException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean canTerminate() {
		return !isTerminated();
	}

	@Override
	public boolean isTerminated() {
		return getDebugTarget().isTerminated();
	}

	@Override
	public void terminate() throws DebugException {
		getDebugTarget().terminate();
	}

	@Override
	public IStackFrame[] getStackFrames() throws DebugException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasStackFrames() throws DebugException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getPriority() throws DebugException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public IStackFrame getTopStackFrame() throws DebugException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() throws DebugException {
		return "HL-Thread";
	}

	@Override
	public IBreakpoint[] getBreakpoints() {
		if (hlBreakpoint == null) {
			return new IBreakpoint[0];
		}
		return new IBreakpoint[] { hlBreakpoint };
	}

	/**
	 * Returns the most revent error event encountered since the last suspend,
	 * or <code>null</code> if none.
	 * 
	 * @return the most revent error event encountered since the last suspend,
	 *         or <code>null</code> if none
	 */
	public Object getError() {
		return fErrorEvent;
	}

	public boolean isfStepping() {
		return fStepping;
	}

	public void setfStepping(boolean fStepping) {
		this.fStepping = fStepping;
	}

	// @Override
	// public void handleEvent(String event) {
	// // clear previous state
	// breakpoint = null;
	// setStepping(false);
	//
	// // handle events
	// if (event.startsWith("resumed")) {
	// setSuspended(false);
	// if (event.endsWith("step")) {
	// setStepping(true);
	// resumed(DebugEvent.STEP_OVER);
	// } else if (event.endsWith("client")) {
	// resumed(DebugEvent.CLIENT_REQUEST);
	// } else if (event.endsWith("drop")) {
	// resumed(DebugEvent.STEP_RETURN);
	// }
	// } else if (event.startsWith("suspended")) {
	// setSuspended(true);
	// if (event.endsWith("client")) {
	// suspended(DebugEvent.CLIENT_REQUEST);
	// } else if (event.endsWith("step")) {
	// suspended(DebugEvent.STEP_END);
	// } else if (event.startsWith("suspended event") && getError() != null) {
	// exceptionHit();
	// } else if (event.endsWith("drop")) {
	// suspended(DebugEvent.STEP_END);
	// }
	// } else if (event.equals("started")) {
	// fireCreationEvent();
	// } else {
	// setError(event);
	// }
	//
	// }
	//
	// public void suspendedBy(HLDebugLineBreakpoint hlDebugBreakpoint) {
	// this.hlBreakpoint = hlDebugBreakpoint;
	// suspended(DebugEvent.BREAKPOINT);
	// }

}
