package com.joe.net.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 演示TCP的传输的客户端和服务端的互访
 * 需求：客户端给服务端发送数据，服务端收到后，给客户端反馈信息
 * 
 * 客户端：
 * 	建立socket服务。指定要连接的主机和端口
 * 	获取socket流中的输出六。将数据写到该流中，通过网络发送给服务端
 * 	获取socket流中的输入流，将服务端反馈的数据获取到，并打印
 * 	关闭客户端
 *
 */
public class TcpServerClient2 {

	public static void main(String[] args) {

	}
}

class TcpClient2 {
	public static void main(String[] args) throws Exception, IOException {
		Socket s = new Socket("16.165.13.46",4444);
		
		OutputStream out = s.getOutputStream();
		out.write("Hello Server!".getBytes());	//阻塞式方法
		
		//如果对方有数据回来，就执行下面的代码。否则在这里等待
		InputStream in = s.getInputStream();
		
		byte[] buf = new byte[1024];
		int len = in.read(buf);
		
		System.out.println(new String(buf,0,len));
		
		s.close();
	}
}

class TcpServer2 {
	public static void main(String[] args) throws Exception {
		//建立server 端socket服务，并监听一个端口
		ServerSocket ss = new ServerSocket(4444);
		
		//accept()方法获取连接过来的客户端对象
		Socket s = ss.accept();
		String ip = s.getInetAddress().getHostAddress();
		System.out.println(ip + ".....connected");
		
		//获取客户端发送过来的数据
		InputStream in = s.getInputStream();
		
		byte[] buf = new byte[1024];
		
		int len = in.read(buf);
		
		System.out.println(new String(buf,0,len));
		
		//给客户端反馈信息
		OutputStream out = s.getOutputStream();
		out.write("Hello, Client!".getBytes());
		s.close();	//关闭客户端
		//ss.close();	可选
	}
}