package com.joe.io;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class PipedStreamDemo {

	public static void main(String[] args) throws IOException {
		PipedInputStream in = new PipedInputStream();
		PipedOutputStream out = new PipedOutputStream();
		
		in.connect(out);
		
		Read r = new Read(in);
		Write w = new Write(out);
		
		new Thread(r).start();
		new Thread(w).start();
	}
}
//运行时，打印：		读取前。没有数据，阻塞
//输入ddd回车。			dddd
//读取后。阻塞结束
//Piped came 
class Read implements Runnable {
	private PipedInputStream in;
	Read(PipedInputStream in) {
		this.in = in;
	}
	
	public void run() {
		try{
			byte[] buf = new byte[1024];
			System.out.println("读取前。没有数据，阻塞");
			
			int len = in.read(buf);
			System.out.println("读取后。阻塞结束");
			
			String s = new String(buf, 0, len);
			
			System.out.println(s);
			in.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}

class Write implements Runnable {
	private PipedOutputStream out;
	Write(PipedOutputStream out) {
		this.out = out;
	}
	
	public void run() {
		try {
			Thread.sleep(5000);
			out.write("Piped came ".getBytes());
			out.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}