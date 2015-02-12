package com.shs.hl.net;

import org.eclipse.debug.core.model.IProcess;

import com.shs.hl.debug.core.breakpoints.HLDebugLineBreakpoint;

public interface IDebugTCPClient extends IProcess {
	public void sendCommand(DebugCommandNet commandNet);

	public void addBreakPoint(HLDebugLineBreakpoint hlDebugLineBreakpoint);
}
