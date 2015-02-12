package com.shs.hl.debug.core.model;

import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.IBreakpointManager;
import org.eclipse.debug.core.model.DebugElement;
import org.eclipse.debug.core.model.IDebugTarget;

import com.shs.hl.debug.core.Constants;

public class HLDebugElement extends DebugElement {

	public HLDebugElement(IDebugTarget ftarget) {
		super(ftarget);
	}

	@Override
	public String getModelIdentifier() {
		return Constants.ID_HL_DEBUG_MODEL;
	}

	protected String sendRequest(String command) {
		return ((HLDebugTarget) getDebugTarget()).sendRequest(command);
	}

	protected IBreakpointManager getBreakpointManager() {
		return DebugPlugin.getDefault().getBreakpointManager();
	}
}
