package com.shs.hl.net.protocol;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jboss.netty.channel.ChannelEvent;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

import com.shs.hl.net.DebugCommandNet;
import com.shs.hl.net.DebugListener;

public class DebugClientHandler extends SimpleChannelUpstreamHandler {

	Logger								logger		= Logger.getLogger("DebugClientHandler");
	private final List<DebugListener>	listeners	= new ArrayList<DebugListener>();

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
		DebugCommandNet command = (DebugCommandNet) e.getMessage();
		for (DebugListener listener : listeners) {
			listener.processCommand(command);
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
		logger.log(Level.SEVERE, e.getCause().getMessage());
	}

	public void registerListeners(DebugListener listener) {
		this.listeners.add(listener);
	}

	public void deregisterLister(DebugListener lister) {
		this.listeners.remove(lister);
	}

	@Override
	public void handleUpstream(ChannelHandlerContext ctx, ChannelEvent e) throws Exception {
		super.handleUpstream(ctx, e);
	}
}
