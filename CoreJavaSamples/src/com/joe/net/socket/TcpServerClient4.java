package com.joe.net.socket;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 需求：建立一个文本转换服务器
 * 
 * 客户端发送文本文件给server，server检测到上传成功后返回信息给client
 */
public class TcpServerClient4 {
	public static void main(String[] args) {

	}
}

class TcpClient4 {
	public static void main(String[] args) throws Exception {
		Socket s = new Socket("16.165.13.46",2222);
		
		//文件读取
		BufferedReader bufr = new BufferedReader(new FileReader("c:\\data.txt"));
		
		PrintWriter out = new PrintWriter(s.getOutputStream(), true);
		
		//时间戳来定义结束标记
//		DataOutputStream dos = new DataOutputStream(s.getOutputStream());
//		long time = System.currentTimeMillis();
//		dos.writeLong(time);
		
		String line = null;
		while((line = bufr.readLine()) != null) {
			out.println(line);
		}
		
		//out.println("over");	//自定义结束标记	,容易和文本中的数据重合
		//dos.writeLong(time);	//再写一遍时间戳
		s.shutdownOutput();	//关闭客户端的输出流，相当于给流中加入一个结束标记		
		
		//定义socket读取流，读取服务端返回的上传成功信息
		BufferedReader bufIn = new BufferedReader(new InputStreamReader(s.getInputStream()));
		String str = bufIn.readLine();
		System.out.println(" messge from Server:" + str);
		
		bufr.close();
		
		s.close();	//当客户端结束后，socket流里会有个标志
	}
}

class TcpServer4{

	public static void main(String[] args) throws Exception {
		//建立server 端socket服务，并监听一个端口
		ServerSocket ss = new ServerSocket(2222);
		
		//accept()方法获取连接过来的客户端对象
		Socket s = ss.accept();
		String ip = s.getInetAddress().getHostAddress();
		System.out.println(ip + ".....connected");
		
//		DataInputStream dis = new DataInputStream(s.getInputStream());
//		long time = dis.readLong();
		
		//获取客户端发送过来的数据
		BufferedReader bufIn = new BufferedReader(new InputStreamReader(s.getInputStream()));
		
		//写文件。此处未考虑文件重名问题
		PrintWriter out = new PrintWriter(new FileWriter("c:\\data2.txt"), true);
		
		String line = null;
		while((line = bufIn.readLine()) != null) {
//			//自定义结束标记
//			if ("over".equals(line))
//				break;
			out.println(line);
		}
		
		//给客户端返回信息
		PrintWriter pw = new PrintWriter(s.getOutputStream(),true);
		pw.println("upload successfully!");
		out.close();
		
		s.close();	//关闭客户端
		
		//ss.close();	可选
	}
}