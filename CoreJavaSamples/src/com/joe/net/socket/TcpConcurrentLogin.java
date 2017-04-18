package com.joe.net.socket;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpConcurrentLogin {
	public static void main(String[] args) {

	}

}

class LoginClient {
	public static void main(String[] args) throws Exception {
		Socket s = new Socket("16.165.13.46",6789);
		
		BufferedReader bufr = new BufferedReader(new InputStreamReader(System.in));
		
		//必须要带自动刷新
		PrintWriter out = new PrintWriter(s.getOutputStream(), true);
		
		//判断server端发送过来的信息
		BufferedReader bufIn = new BufferedReader(new InputStreamReader(s.getInputStream()));
		
		//3次以上禁止再登录
		for (int x=0;x<3;x++) {
			String line = bufr.readLine();
			//System.out.println("LoginClient.line:" + line);
			//when ctrl+C
			if(line == null)
				break;
			
			//写给serverSocket
			out.println(line);
			
			String info = bufIn.readLine();
			System.out.println("info from server:" + info);
			
			if (info.contains("welcome"))
				break;
		}
		
		bufr.close();
		s.close();
	}
}

class LoginServer {
	public static void main(String[] args) throws Exception {
		ServerSocket ss = new ServerSocket(6789);
		
		while(true) {
			Socket s = ss.accept();
			
			new Thread(new UserThread(s)).start();
		}
	}
}

/**
 * c:\\user.txt里的内容
 * 		zhangsan
 * 		lisi
 * 		wangwu
 * 
 * 
 * @author xiaojun
 *
 */
class UserThread implements Runnable {
	private Socket s;
	UserThread(Socket s) {
		this.s = s;
	}
	
	@Override
	public void run() {
		String ip = s.getInetAddress().getHostAddress();
		System.out.println(ip + ".....connected");
		
		try{
			//3次以上禁止再登录
			for (int x=0;x<3;x++) {
				//获取客户端发送过来的socket流
				BufferedReader bufIn = new BufferedReader(new InputStreamReader(s.getInputStream()));
				
				//客户端输入的名字
				String name = bufIn.readLine();
				if (name == null)
					break;
				
				//查找文件。
				BufferedReader bufr = new BufferedReader(new FileReader("c:\\user.txt"));
				
				//向客户端报信息
				PrintWriter out = new PrintWriter(s.getOutputStream(),true);
				
				String line = null;
				boolean flag = false;
				while((line = bufr.readLine()) != null) {
					//如果客户端输入的用户名在user.txt里是存在的
					if (line.equals(name)) {
						flag = true;
						break;
					}
				}
				
				if (flag) {
					System.out.println(name + " has logged in");
					out.println(name + ", welcome ");
					break;
				} else {
					System.out.println(name + " is trying to log in");
					out.println(name + ", not exists in user.txt ");
				}
			}
			
			s.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}