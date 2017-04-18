package com.joe.net.socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * 通过UDP传输方式，将一段文字数据发送出去。
 * 
 * @author xiaojun
 *
 */
public class UdpSender {

	/**
	 * @param args
	 * @throws SocketException 
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		//fixedData();
		
		dynamicData();
	}
	
	//键盘录入
	public static void dynamicData() throws Exception{
		//创建UDP服务。通过DatagramSocket对象
		DatagramSocket ds = new DatagramSocket();
		
		//确定数据，并封装成数据包
		BufferedReader bufr = new BufferedReader(new InputStreamReader(System.in));
		
		String line = null;
		while((line = bufr.readLine()) != null) {
			if ("886".equals(line)) {
				break;
			}
			byte[] data = line.getBytes();
			DatagramPacket dp = new DatagramPacket(data, data.length, InetAddress.getByName("127.0.0.1"),8888);
			
			//通过socket服务，将已有的数据包发送出去。
			ds.send(dp);
		}
		
		//关闭资源
		ds.close();
	}
	
	public static void fixedData() throws Exception{
		//创建UDP服务。通过DatagramSocket对象
		DatagramSocket ds = new DatagramSocket();
		
		//确定数据，并封装成数据包
		byte[] data = "udp data sample comes".getBytes();
		DatagramPacket dp = new DatagramPacket(data, data.length, InetAddress.getByName("127.0.0.1"),8888);
		
		//通过socket服务，将已有的数据包发送出去。
		ds.send(dp);
		
		//关闭资源
		ds.close();
	}
}