package com.nianbaofenbao;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket socket = new Socket("127.0.0.1", 10101);
		OutputStream outputStream = socket.getOutputStream();
		String message = "hello";
		byte[] bytes = message.getBytes();
		
		for(int i=0;i<1000;i++){
			outputStream.write(bytes);
		}
		outputStream.close();

	}

}
