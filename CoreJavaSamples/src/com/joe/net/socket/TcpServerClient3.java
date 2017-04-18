package com.joe.net.socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 需求：建立一个文本转换服务器
 * 
 * 客户端发送文本信息给server，server转换成大写返回给client
 * 
 * client:
 * 		源:		键盘录入
 * 		目的：	网络设备，网络输出流 
 * 		操作的是文本数据，可以选择字符流
 *
 */
public class TcpServerClient3 {
	public static void main(String[] args) {

	}
}

class TcpClient3 {
	public static void main(String[] args) throws Exception {
		Socket s = new Socket("16.165.13.46",3333);
		
		//键盘录入
		BufferedReader bufr = new BufferedReader(new InputStreamReader(System.in));
		
		//定义目的，将数据写入到socket输出流。发给服务端
		//BufferedWriter bufOut = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
		//用PrintWriter来替换上面的BufferedWriter也可以
		PrintWriter out = new PrintWriter(s.getOutputStream(), true);
		
		//定义socket读取流，读取服务端返回的大写信息
		BufferedReader bufIn = new BufferedReader(new InputStreamReader(s.getInputStream()));
		
		String line = null;
		while((line = bufr.readLine()) != null) {
			if ("over".equals(line))
				break;
			
//			bufOut.write(line);
//			bufOut.newLine();	//必须换行，不然服务端的readLine()读不到数据
//			bufOut.flush();	//必须刷新缓冲区，不然数据写不出去
			
			out.println(line.toUpperCase());
			
			String str = bufIn.readLine();
			System.out.println(" messge from Server:" + str);
		}
		
		bufr.close();
		
		s.close();	//当客户端结束后，socket流里会有个标志
	}
}

/**
 * 源： socket读取流
 * 目的：socket输出流
 * 
 */
class TcpServer3 {

	public static void main(String[] args) throws Exception {
		//建立server 端socket服务，并监听一个端口
		ServerSocket ss = new ServerSocket(3333);
		
		//accept()方法获取连接过来的客户端对象
		Socket s = ss.accept();
		String ip = s.getInetAddress().getHostAddress();
		System.out.println(ip + ".....connected");
		
		//获取客户端发送过来的数据
		BufferedReader bufIn = new BufferedReader(new InputStreamReader(s.getInputStream()));
		
		//目的：socket输出流，将大写数据写入到socket输出流，并发送给client
		//BufferedWriter bufOut = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
		//用PrintWriter来替换上面的BufferedWriter也可以
		PrintWriter out = new PrintWriter(s.getOutputStream(), true);
		
		String line = null;
		while((line = bufIn.readLine()) != null) {
//			bufOut.write(line.toUpperCase());
//			
//			bufOut.newLine();	//必须换行，不然服务端的readLine()读不到数据
//			bufOut.flush();	//必须刷新缓冲区，不然数据写不出去
			
			out.println(line.toUpperCase());
		}
		s.close();	//关闭客户端
		
		//ss.close();	可选
	}
}