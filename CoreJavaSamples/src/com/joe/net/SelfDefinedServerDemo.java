package com.joe.net;

import java.io.InputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 服务器端自定义
 * 然后用浏览器访问 http://localhost:10000
 * 
 * result: 
 * 	浏览器里显示：	welcome, client!
 * 	控制台打印：	0:0:0:0:0:0:0:1
 *
 */
public class SelfDefinedServerDemo {

	public static void main(String[] args) throws Exception {
		ServerSocket ss = new ServerSocket(10000);
		
		Socket s = ss.accept();
		System.out.println(s.getInetAddress().getHostAddress());
		
		//server端读取client端的输入信息
		InputStream in = s.getInputStream();
		byte[] buf = new byte[1024];
		int len = in.read(buf);
		System.out.println(new String(buf, 0, len));
		
		PrintWriter out = new PrintWriter(s.getOutputStream(),true);
		
		out.println("welcome, client!");
		
		s.close();
		
		ss.close();
	}
}

//控制台打印：
//0:0:0:0:0:0:0:1
//GET / HTTP/1.1
//Host: localhost:10000
//User-Agent: Mozilla/5.0 (Windows NT 6.1; WOW64; rv:26.0) Gecko/20100101 Firefox/26.0
//Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8
//Accept-Language: zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3
//Accept-Encoding: gzip, deflate
//Connection: keep-alive