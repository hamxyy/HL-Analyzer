package com.shs.hl.net;

import java.nio.charset.Charset;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.string.StringDecoder;

public class DebugCommandDecoder extends StringDecoder {

	private final Charset	charset;

	public DebugCommandDecoder(Charset charset) {
		this.charset = charset;
	}

	@Override
	protected Object decode(ChannelHandlerContext ctx, Channel channel, Object msg) throws Exception {
		if (!(msg instanceof ChannelBuffer)) {
			return msg;
		}
		String received = ((ChannelBuffer) msg).toString(charset);
		if (!received.trim().isEmpty()) {
			String[] tokens = received.split("#");

			// #reply#3#getBreakpoints##$file1.hl:15$file5.hl:105#
			String commandType = tokens[1];
			int id = Integer.valueOf(tokens[2]);
			String command = tokens[3];
			String payload = tokens[4];
			String result = tokens[5];
			return new DebugCommandNet(id, commandType, command, payload, result);
		}
		return null;
	}

}
