package com.nianbaofenbao;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

public class HelloHander extends SimpleChannelHandler{
	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
		 ChannelBuffer message = (ChannelBuffer) e.getMessage();
		 byte[] array = message.array();
		 //转换为字符串
		 System.out.println("##"+new String(array));
	}
}
