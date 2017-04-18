package com.joe.net;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 显示网站的IP
 * 打印本机的机器名和IP
 * 
 * @author JoeXIAO
 *
 */
public class InetAddressUsage {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		InetAddress cumt = null;
		InetAddress address = null;
		
		try {  
			cumt = InetAddress.getByName("www.baidu.com");
			address = InetAddress.getLocalHost();
		}catch (UnknownHostException e) { 
			e.printStackTrace();
		}
		//www.baidu.com/220.181.111.147
		System.out.println(cumt);
		
		//IBM059-PC01WYFL/9.111.41.50
		System.out.println(address);  
	}

}
