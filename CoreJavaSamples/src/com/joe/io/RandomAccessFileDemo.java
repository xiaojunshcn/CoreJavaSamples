package com.joe.io;

import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFileDemo {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		//writeFile();	//里斯   a王武   c
		readFile();
	}

	public static void readFile() throws IOException {
		RandomAccessFile raf = new RandomAccessFile("c:\\ran.txt","r");
		
		//raf.write("haha".getBytes());	//Access is denied	写不进去，因为是只读的：r
		
		//调整对象中指针
		raf.seek(8);
		
		byte[] buf = new byte[4];
		raf.read(buf);
		String s = new String(buf);
		
		int age = raf.readInt();
		
		System.out.println("name:" + s);
		System.out.println("age:" + age);
		raf.close();
	}
	
	public static void writeFile() throws IOException {
		RandomAccessFile raf = new RandomAccessFile("c:\\ran.txt","rw");
		
		raf.write("里斯".getBytes());
		raf.writeInt(97);
		raf.write("王武".getBytes());
		raf.writeInt(99);
		
		raf.close();
	}
}