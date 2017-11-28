package com.handlenianbaofenbao;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;

public class Client {

	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket socket = new Socket("127.0.0.1", 10101);
		OutputStream outputStream = socket.getOutputStream();
		String message = "hello";
		byte[] bytes = message.getBytes();
		
		/**
		 * 处理粘包分包:就是使用：长度+数据
		 */
		ByteBuffer buffer = ByteBuffer.allocate(4+bytes.length);//使用allocate()静态方法,创建一个容量为4+bytes.length字节的ByteBuffer
		buffer.putInt(bytes.length);
		buffer.put(bytes);
		byte[] array = buffer.array();
		
		for(int i=0;i<1000;i++){
			outputStream.write(array);
		}
		outputStream.close();

	}

}
