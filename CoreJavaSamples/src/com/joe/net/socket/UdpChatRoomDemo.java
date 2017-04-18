package com.joe.net.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * UDP方式
 */
public class UdpChatRoomDemo {
	public static void main(String[] args) throws Exception {
		DatagramSocket sendSocket = new DatagramSocket();
		DatagramSocket receiveSocket = new DatagramSocket(6677);
		
		new Thread(new Send(sendSocket)).start();
		new Thread(new Receive(receiveSocket)).start();
	}
}
//运行时显示：		receive: before
//输入			jjjj
//send:line=jjjj
//send: after sending
//receive: after
//16.165.13.46:jjjj
//receive: before
class Send implements Runnable {
	private DatagramSocket ds;
	public Send(DatagramSocket ds) {
		this.ds = ds;
	}
	@Override
	public void run() {
		//读键盘
		try{
			BufferedReader bufr = new BufferedReader(
					new InputStreamReader(System.in));
			
			String line = null;
			while((line = bufr.readLine()) != null) {
				System.out.println("send:line=" + line);
				if ("886".equals(line))
					break;
				
				byte[] buf = line.getBytes();
				//DatagramPacket dp = new DatagramPacket(buf,buf.length,InetAddress.getByName("127.0.0.1"),6666);
				DatagramPacket dp = new DatagramPacket(buf,buf.length,
						InetAddress.getByName("16.165.13.46"),6677);
				
				ds.send(dp);
				System.out.println("send: after sending");
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}

class Receive implements Runnable {
	private DatagramSocket ds;
	public Receive(DatagramSocket ds) {
		this.ds = ds;
	}
	@Override
	public void run() {
		try{
			while(true) {
				
				byte[] buf = new byte[1024];
				DatagramPacket dp = new DatagramPacket(buf, buf.length);
				System.out.println("receive: before");
				ds.receive(dp);
				System.out.println("receive: after");
				String ip = dp.getAddress().getHostAddress();
				
				String data = new String(dp.getData(),0, dp.getLength());
				
				System.out.println(ip+":" +data);
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}