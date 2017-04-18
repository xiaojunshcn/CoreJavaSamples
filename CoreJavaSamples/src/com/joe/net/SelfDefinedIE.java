package com.joe.net;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 在apache-tomcat-6.0.36\webapps下新建myweb目录，在其内放置demo.htm
 * 
 * 启动本地的tomcat服务器
 * 先用IE浏览器验证是否正常访问
 * 
 * 再运行本程序，检查是否有demo.htm里的内容输出
 * 
 */
public class SelfDefinedIE {

	public static void main(String[] args) throws Exception {
		//访问本地tomcat服务器
		Socket s = new Socket("localhost",8080);
		
		PrintWriter out = new PrintWriter(s.getOutputStream(), true);
		//GET / HTTP/1.1
		//Host: localhost:10000
		//User-Agent: Mozilla/5.0 (Windows NT 6.1; WOW64; rv:26.0) Gecko/20100101 Firefox/26.0
		//Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8
		//Accept-Language: zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3
		//Accept-Encoding: gzip, deflate
		//Connection: keep-alive
		
		//访问tomcat webapp目录下的myweb目录下的demo页面
		out.println("GET /myweb/demo.htm HTTP/1.1");
		out.println("Accept: */*");
		out.println("Accept-Language: zh-cn,zh;");
		out.println("Host: localhost:8080");
		out.println("Connection: keep-alive");
		out.println();
		
		BufferedReader bufr = new BufferedReader(new InputStreamReader(s.getInputStream()));
		
		String line = null;
		while((line = bufr.readLine()) != null) {
			System.out.println(line);
		}
		
		s.close();
	}

}

//运行结果：
//HTTP/1.1 200 OK
//Server: Apache-Coyote/1.1
//Accept-Ranges: bytes
//ETag: W/"85-1394777462158"
//Last-Modified: Fri, 14 Mar 2014 06:11:02 GMT
//Content-Type: text/html
//Content-Length: 85
//Date: Fri, 14 Mar 2014 06:11:49 GMT
//
//<html>
//  <body>
//	<p>shanghai</p>
//	<p align="right">china</p>
//  </body>
//</html>
