package com.shs.hl.net;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.handler.codec.frame.DelimiterBasedFrameDecoder;
import org.jboss.netty.handler.codec.frame.Delimiters;
import org.jboss.netty.handler.codec.string.StringEncoder;
import org.jboss.netty.util.CharsetUtil;

import com.shs.hl.net.protocol.DebugClientHandler;

public class DebugPipelineFactory implements ChannelPipelineFactory {

	@Override
	public ChannelPipeline getPipeline() throws Exception {
		ChannelPipeline pipeline = Channels.pipeline();
		pipeline.addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
		pipeline.addLast("decoder", new DebugCommandDecoder(CharsetUtil.US_ASCII));
		pipeline.addLast("encoder", new StringEncoder(CharsetUtil.US_ASCII));
		pipeline.addLast("handler", new DebugClientHandler());
		return pipeline;
	}

}
