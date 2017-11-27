package com.cn.code;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.frame.FrameDecoder;

import com.cn.constants.Constants;
import com.cn.model.Request;
import com.cn.model.Response;

/**
 * 请求解码器
 * <pre>
 * 数据包格式
  +——----+——----+——----——+——----+——--------——+
 * | 包头   |模块号    |命令号    |状态码  |长度  | 数据     |
 * +——----+——----+——----——+——----+——--------——+
 * </pre>
 * 包头4字节
 * 模块号2字节short
 * 命令号2字节short
 * 长度4字节(描述数据部分字节长度)
 */
public class ResponseDecoder extends FrameDecoder{
   /**
    * 数据基本长度
    */
	public static int BASE_LENGTH = 4+2+2+4;//包头+模块号+命令号+长度

@Override
protected Object decode(ChannelHandlerContext ctx, Channel channel,
		ChannelBuffer buffer) throws Exception {
	//记录包开头的index
	int beginIndex = buffer.readerIndex();
	
	//可读长度必须大于基本长度
	if(buffer.readableBytes()>=BASE_LENGTH){
		while(true){//循环读取,读到包头flag为止才可以真正读取数据
			if(buffer.readInt()==Constants.FLAG){
				break;
			}
		}
		//模块号
		short module = buffer.readShort();
		//命令号
		short cmd = buffer.readShort();
		//状态码
		int statusCode = buffer.readInt();
		//长度
		int length = buffer.readInt();
		
		//现在可读长度小于数据长度,说明数据还没有读完
		if(buffer.readableBytes()<length){
			//已经读取了12字节数据,现在的数据还没有来齐,所以还原读指针,返回空数据
			buffer.readerIndex(beginIndex);
			return null;
		}
		
		//如果数据来全之后(按照长度读取)
		byte[] data = new byte[length];
		buffer.readBytes(data);
		
		//构建 buffer-->request
		Response response = new Response();  
		response.setModule(module);
		response.setCmd(cmd);
		response.setStatusCode(statusCode);
		response.setData(data);
		
		return response;
		
	}
	return null;
  }
}

















