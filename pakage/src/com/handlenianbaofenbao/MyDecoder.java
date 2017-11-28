package com.handlenianbaofenbao;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.frame.FrameDecoder;

public class MyDecoder extends FrameDecoder {
	@Override
	protected Object decode(ChannelHandlerContext ctx, Channel channel,
			ChannelBuffer buffer) throws Exception {
		//如果可读长度比基本长度大,我们才读取数据
		if(buffer.readableBytes()>4){
			//标记
			buffer.markReaderIndex();
			//长度(int为4字节)
			int length = buffer.readInt();
			if(buffer.readableBytes()<length){
				buffer.resetReaderIndex();
				//缓存当前剩余的buffer数据,等待剩下数据包到来
				return null;
			}
			//读数据
			byte[] bytes = new byte[length];
			buffer.readBytes(bytes);
			//往下传递对象
			return new String(bytes);
		}
		return null;
	}
}
