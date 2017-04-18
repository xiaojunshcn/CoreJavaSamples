package com.joe.net;

import java.io.DataInputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * 两种方式读取URL的内容
 * 
 * @author JoeXIAO
 *
 */
public class ReadContentsFromUrl {

	public static void main(String[] args) {
		methodUrl();
		
		System.out.println("---------------");
		methodURLConnection();
	}

	private static void methodUrl() {
		try {
			//when in an intranet, this 2 lines are required
//			System.setProperty("http.proxyHost", "proxy.jpn.hp.com");
//			System.setProperty("http.proxyPort", "8080");
			
			// 根据参数args[0])构造一个绝对的URL对象
			URL url = new URL("http://www.baidu.com/");
			
			// 通过URL对象打开一个数据输入流
			DataInputStream dis = new DataInputStream(url.openStream());
			
			String inputLine;
			while ((inputLine = dis.readLine()) != null) {
				System.out.println(inputLine);
			}
			dis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void methodURLConnection() {
		try {
			// when in an intranet, this 2 lines are required
//			System.setProperty("http.proxyHost", "proxy.jpn.hp.com");
//			System.setProperty("http.proxyPort", "8080");
			
			// 根据绝对URL地址构造URL对象
			URL cumtURL = new URL("http://www.baidu.com");
			
			// 利用cumtURL建立一个URLconnection连接
			URLConnection cumtConnection = cumtURL.openConnection();
			
			// 获取cumtConnection对象的数据输入流
			DataInputStream din = new DataInputStream(cumtConnection.getInputStream());
			
			String inputLine;
			while ((inputLine = din.readLine()) != null) {
				System.out.println(inputLine);
			}
			din.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
