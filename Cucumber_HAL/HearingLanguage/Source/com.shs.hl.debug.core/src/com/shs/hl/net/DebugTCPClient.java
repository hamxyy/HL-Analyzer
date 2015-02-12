package com.shs.hl.net;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.model.IStreamsProxy;
import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelFutureListener;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;

import com.shs.hl.debug.core.breakpoints.HLDebugLineBreakpoint;

public class DebugTCPClient implements IDebugTCPClient {
	private final String host;
	private final int tcpPort;
	Channel channel;
	ClientBootstrap bootstrap;
	private final ILaunch launch;
	Map<String, String> attributes = new HashMap<String, String>();

	public DebugTCPClient(String host, int port, ILaunch launch) {
		this.host = host;
		this.tcpPort = port;
		this.launch = launch;
		startDebugSession();
	}

	public void startDebugSession() {

		bootstrap = new ClientBootstrap(new NioClientSocketChannelFactory(
				Executors.newCachedThreadPool(),
				Executors.newCachedThreadPool()));
		bootstrap.setPipelineFactory(new DebugPipelineFactory());

		ChannelFuture future = bootstrap.connect(new InetSocketAddress(host,
				tcpPort));
		if (!future.isSuccess()) {
			// future.getCause().printStackTrace();
			// bootstrap.releaseExternalResources();
			// return;
		}
		channel = future.awaitUninterruptibly().getChannel();
		channel.getCloseFuture().addListener(new ChannelFutureListener() {
			@Override
			public void operationComplete(ChannelFuture future)
					throws Exception {
				new Thread(new Runnable() {
					@Override
					public void run() {
						bootstrap.releaseExternalResources();
					}
				}).start();
			}
		});
	}

	public static void main(String[] args) throws DebugException {
		DebugTCPClient client = new DebugTCPClient("localhost", 6001, null);
		client.channel.write("#cmd#820693300#add#FF.FirstFit.hl$61##");
		client.terminate();

		client = new DebugTCPClient("localhost", 5000, null);
		client.channel.write("#cmd#820693#setBreakPoint#FF.FirstFit.cs$65##");
		client.terminate();
	}

	@Override
	public void sendCommand(DebugCommandNet commandNet) {
		channel.write(commandNet.toString());
	}

	public void stopDebugSession() {
		channel.close();
	}

	@Override
	public Object getAdapter(@SuppressWarnings("rawtypes") Class adapter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean canTerminate() {
		return true;
	}

	@Override
	public boolean isTerminated() {

		return false;
	}

	@Override
	public void terminate() throws DebugException {
		stopDebugSession();
		// bootstrap.shutdown();

	}

	@Override
	public String getLabel() {
		return "HL-Debugee";
	}

	@Override
	public ILaunch getLaunch() {
		return launch;
	}

	@Override
	public IStreamsProxy getStreamsProxy() {
		// TODO Auto-generated method stub
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
	public void addBreakPoint(HLDebugLineBreakpoint hlDebugLineBreakpoint) {

	}
}
