package com.shs.hl.net.protocol;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

public class InfoMessageProtocolHandler extends SimpleChannelUpstreamHandler {

	public InfoMessageProtocolHandler() {

	}

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
			throws Exception {
		// DebugCommandNet netCommand = (DebugCommandNet) e.getMessage();

	}

}
