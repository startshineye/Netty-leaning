package com.handlenianbaofenbao;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
public class HelloHander extends SimpleChannelHandler{
	private int count = 1;
	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
		//经过编码器返回的就是String,所以直接输出
		
		System.out.println(e.getMessage()+"  "+count);
		count++;
	}
}
