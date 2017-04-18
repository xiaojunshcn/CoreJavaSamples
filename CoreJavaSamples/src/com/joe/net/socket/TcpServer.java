package com.joe.net.socket;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 定义端点接受数据并打印在控制台上
 * 
 */
public class TcpServer {
	public static void main(String[] args) throws Exception {
		//建立server 端socket服务，并监听一个端口
		ServerSocket ss = new ServerSocket(5555);
		
		//accept()方法获取连接过来的客户端对象
		Socket s = ss.accept();
		String ip = s.getInetAddress().getHostAddress();
		System.out.println(ip + ".....connected");
		
		//获取客户端发送过来的数据
		InputStream in = s.getInputStream();
		
		byte[] buf = new byte[1024];
		
		int len = in.read(buf);
		
		System.out.println(new String(buf,0,len));
		
		s.close();	//关闭客户端
		
		//ss.close();	可选
	}
}
