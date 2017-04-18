package com.joe.net;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class URLConnectionDemo {
	public static void main(String[] args) throws Exception {
		URL url = new URL("http://localhost:8080/myweb/demo.htm");
		
		URLConnection conn = url.openConnection();
		//sun.net.www.protocol.http.HttpURLConnection:http://localhost:8080/myweb/demo.htm
		System.out.println(conn);
		
		//URLConnection封装socket对象,所以它也有getInputStream()
		//socket是传输层，http是应用层
		InputStream in = conn.getInputStream();
		
		byte[] buf = new byte[1024];
		int len = in.read(buf);
		
		//输出就不再带响应头信息了
//		<html>
//		  <body>
//			<p>shanghai</p>
//			<p align="right">china</p>
//		  </body>
//		</html>
		System.out.println(new String(buf, 0, len));
	}
}