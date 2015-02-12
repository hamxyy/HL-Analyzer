package com.shs.hl.debug.ui.launching;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.ILaunchConfigurationDelegate;

import com.shs.hl.debug.core.model.HLDebugTarget;
import com.shs.hl.debug.core.remote.HLDebugComm;
import com.shs.hl.net.DebugTCPClient;

public class HLDebugLaunchDelegate implements ILaunchConfigurationDelegate {

	@Override
	public void launch(ILaunchConfiguration configuration, String mode, ILaunch launch, IProgressMonitor monitor) throws CoreException {

		int requestPort = -1;
		int eventPort = -1;
		if (mode.equals(ILaunchManager.DEBUG_MODE)) {
			requestPort = HLDebugComm.getFreePort();
			eventPort = HLDebugComm.getFreePort();
			if (requestPort == -1 || eventPort == -1) {
				abort("Unable to find free port", null);
			}
		}

		if (mode.equals(ILaunchManager.DEBUG_MODE)) {
			// TODO IProcess removed
			IDebugTarget target = new HLDebugTarget(launch, new DebugTCPClient("localhost", 5000, launch));
			launch.addDebugTarget(target);
		}
	}

	// TODO :
	private void abort(String message, Throwable e) throws CoreException {
		throw new CoreException(null);
	}
}
