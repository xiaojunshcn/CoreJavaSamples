package com.joe.net.socket;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 需求：建立一个文本转换服务器
 * 
 * 客户端发送文本文件给server，server检测到上传成功后返回信息给client
 *
 */
public class TcpConcurrentUploadFile {

	public static void main(String[] args) {

	}
}

class PhotoClient2 {
	public static void main(String[] args) throws Exception {
//		if (args.length != 1) {
//			System.out.println("please choose a jpg file!");
//			return;
//		}
//		
//		File file = new File(args[0]);
//		if (!(file.exists() && file.isFile())) {
//			System.out.println("file is not");
//			return;
//		}
//		
//		if (!file.getName().endsWith(".jpg")) {
//			System.out.println("not a jpg");
//			return;
//		}
//		
//		if (file.length() > 1024*1024*8) {
//			System.out.println("too large");
//			return;
//		}
		
		Socket s = new Socket("16.165.13.46",1945);
		
		FileInputStream fis = new FileInputStream("c:\\1.jpg");
		
		OutputStream out = s.getOutputStream();
		
		byte[] buf = new byte[1024];
		int len = 0;
		while((len = fis.read(buf)) != -1) {
			out.write(buf,0,len);
		}
		
		//告诉server，数据已近写完
		s.shutdownOutput();
		
		//读server端给client端的返回信息
		InputStream in = s.getInputStream();
		byte[] bufIn = new byte[1024];
		
		int num = in.read(bufIn);
		System.out.println(new String(bufIn, 0, num));

		fis.close();
		
		s.close();	//当客户端结束后，socket流里会有个标志
	}
}

/**
 * 开启多个客户端线程
 * 
 * TcpUploadFile的PhotoServer局限性：
 * 		当A客户端连接后，被服务端获取到，服务端执行具体流程。
 * 		B客户端来连接时，只能等待
 *
 */
class PhotoServer2{

	public static void main(String[] args) throws Exception {
		//建立server 端socket服务，并监听一个端口
		ServerSocket ss = new ServerSocket(1945);
		
		while(true) {
			//accept()方法获取连接过来的客户端对象
			Socket s = ss.accept();
		
			new Thread(new PhotoThread(s)).start();
		}
		//ss.close();	可选
	}
}

class PhotoThread implements Runnable {
	private Socket s;
	PhotoThread(Socket s) {
		this.s = s;
	}
	
	@Override
	public void run() {
		String ip = s.getInetAddress().getHostAddress();
		int count =1;	//对文件名计数
		try {
			
			System.out.println(ip + ".....connected");
			
			//获取客户端发送过来的数据
			InputStream in = s.getInputStream();
			//FileOutputStream fos = new FileOutputStream("c:\\copied_1.jpg");
			//解决同名覆盖问题
			File file = new File("c:\\" + ip+"(" + count + ").jpg");
			while (file.exists())
				file = new File("c:\\" +ip+"(" + (count++) + ").jpg");
			
			FileOutputStream fos = new FileOutputStream(file);
			
			byte[] buf = new byte[1024];
			int len = 0;
			while((len = in.read(buf)) != -1) {
				fos.write(buf, 0, len);
			}
			
			//给客户端返回信息
			OutputStream out = s.getOutputStream();
			out.write("upload successfully!".getBytes());
			fos.close();
			
			s.close();	//关闭客户端
		} catch(Exception e) {
			System.out.println(ip + ".....not connected");
			e.printStackTrace();
		}
	}
}