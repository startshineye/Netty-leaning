package com.cn.code;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;

import com.cn.constants.Constants;
import com.cn.model.Request;

/**
 * 请求编码器
 * * <pre>
 * 数据包格式
 * +——----——+——-----——+——----——+——----——+——-----——+
 * | 包头          | 模块号        | 命令号      |  长度        |   数据       |
 * +——----——+——-----——+——----——+——----——+——-----——+
 * </pre>
 * 包头4字节
 * 模块号2字节short
 * 命令号2字节short
 * 长度4字节(描述数据部分字节长度)
 * @author yxm
 *
 */
public class RequestEncoder extends OneToOneEncoder{

	@Override
	protected Object encode(ChannelHandlerContext ctx, Channel channel,
			Object msg) throws Exception {
		Request request = (Request)msg;
		
		ChannelBuffer buffer = ChannelBuffers.dynamicBuffer();
		//包头   
		buffer.writeInt(Constants.FLAG);
		//模块号
		buffer.writeShort(request.getModule());
		//命令号  
		buffer.writeShort(request.getCmd());
		// 长度   
		buffer.writeInt(request.getDataLength());
		//数据(数据不为空,才读取到buffer)
		if(request.getData()!=null){
			buffer.writeBytes(request.getData());
		}
		return buffer;
	}
}





















