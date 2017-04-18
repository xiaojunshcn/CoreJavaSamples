package com.joe.net.socket;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UdpReceiver {
	public static void main(String[] args) throws Exception {
		//create UDP socket. only 1 time
		DatagramSocket ds = new DatagramSocket(8888);
		
		while(true) {
			//define data packet
			byte[] buf = new byte[1024];
			DatagramPacket dp = new DatagramPacket(buf,buf.length);
	
			//receive data
			ds.receive(dp);	//阻塞式方法
			
			String ip = dp.getAddress().getHostAddress();
			
			String data = new String(dp.getData(),0,dp.getLength());
			
			int port = dp.getPort();
			
			//127.0.0.1:udp data sample comes:58346
			System.out.println(ip + ":" + data + ":" + port);
		}
		
		//close it when necessary
		//ds.close();
	}
}
