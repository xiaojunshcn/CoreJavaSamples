package com.joe.net.socket;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 需求：建立一个文本转换服务器
 * 
 * 客户端发送文本文件给server，server检测到上传成功后返回信息给client
 *
 */
public class TcpUploadFile {
	public static void main(String[] args) {

	}
}

class PhotoClient {
	public static void main(String[] args) throws Exception {
		Socket s = new Socket("16.165.13.46",1111);
		
		FileInputStream fis = new FileInputStream("c:\\1.jpg");
		
		OutputStream out = s.getOutputStream();
		
		byte[] buf = new byte[1024];
		int len = 0;
		while((len = fis.read(buf)) != -1) {
			out.write(buf,0,len);
		}
		
		//告诉server，数据已近写完
		s.shutdownOutput();
		
		//读server端给client端的返回信息
		InputStream in = s.getInputStream();
		byte[] bufIn = new byte[1024];
		
		int num = in.read(bufIn);
		System.out.println(new String(bufIn, 0, num));

		fis.close();
		
		s.close();	//当客户端结束后，socket流里会有个标志
	}
}

class PhotoServer{
	public static void main(String[] args) throws Exception {
		//建立server 端socket服务，并监听一个端口
		ServerSocket ss = new ServerSocket(1111);
		
		//accept()方法获取连接过来的客户端对象
		Socket s = ss.accept();
		String ip = s.getInetAddress().getHostAddress();
		System.out.println(ip + ".....connected");
		
		//获取客户端发送过来的数据
		InputStream in = s.getInputStream();
		FileOutputStream fos = new FileOutputStream("c:\\copied_1.jpg");
		
		byte[] buf = new byte[1024];
		int len = 0;
		while((len = in.read(buf)) != -1) {
			fos.write(buf, 0, len);
		}
		
		//给客户端返回信息
		OutputStream out = s.getOutputStream();
		out.write("upload successfully!".getBytes());
		fos.close();
		
		s.close();	//关闭客户端
		
		//ss.close();	可选
	}
}