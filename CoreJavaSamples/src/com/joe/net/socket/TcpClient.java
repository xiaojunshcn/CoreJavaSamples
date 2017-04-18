package com.joe.net.socket;

import java.io.OutputStream;
import java.net.Socket;

/**
 * 往服务端发送一个文本数据
 *
 */
public class TcpClient {

	public static void main(String[] args) throws Exception {
		sendFixedData();
	}

	public static void sendFixedData() throws Exception{
		Socket s = new Socket("127.0.0.1",5555);
		
		//为了发送数据，应该获取socket中的输出流
		OutputStream out = s.getOutputStream();
		
		out.write("tcp data comes".getBytes());
		
		s.close();
	}
}
