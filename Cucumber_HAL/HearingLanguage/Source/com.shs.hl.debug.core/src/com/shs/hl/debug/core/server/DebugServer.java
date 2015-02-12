package com.shs.hl.debug.core.server;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.model.IProcess;
import org.eclipse.debug.core.model.IStreamsProxy;

import com.shs.hl.net.DebugCommandNet;
import com.shs.hl.net.DebugListener;
import com.shs.hl.net.DebugTCPClient;

public class DebugServer implements IProcess, DebugListener {

	private final String name;
	Map<String, String> attributes = new HashMap<String, String>();
	private final ILaunch launch;
	Set<Integer> linesbreaks = new HashSet<Integer>();
	DebugTCPClient client;

	public DebugServer(final int fport, String fname, ILaunch flaunch) {
		this.name = fname;
		this.launch = flaunch;

	}

	@Override
	public Object getAdapter(@SuppressWarnings("rawtypes") Class adapter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean canTerminate() {
		// TODO
		return true;
	}

	@Override
	public boolean isTerminated() {
		// TODO:
		return false;
	}

	@Override
	public void terminate() throws DebugException {
		client.stopDebugSession();
	}

	@Override
	public String getLabel() {
		return name;
	}

	@Override
	public ILaunch getLaunch() {
		return launch;
	}

	@Override
	public IStreamsProxy getStreamsProxy() {
		return null;
	}

	@Override
	public void setAttribute(String key, String value) {
		attributes.put(key, value);

	}

	@Override
	public String getAttribute(String key) {
		return attributes.get(key);
	}

	@Override
	public int getExitValue() throws DebugException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void processCommand(DebugCommandNet command) {
		// todo
	}
}
