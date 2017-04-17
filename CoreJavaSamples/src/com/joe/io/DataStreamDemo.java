package com.joe.io;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * DataOutputStream/DataInputStream 方式读写文件
 * 
 * @author JoeXIAO
 *
 */
public class DataStreamDemo {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		//writeData();
		
		//readData();
		
		//writeUTF();
		
		readUTF();
	}

	public static void writeData() throws IOException {
		DataOutputStream dos = new DataOutputStream(new FileOutputStream("c:\\data.txt"));
		
		dos.writeInt(234);	//int: 4 bytes
		dos.writeBoolean(true);	//1 byte
		dos.writeDouble(9887.543);//double: 8 bytes
		
		dos.close();
	}
	
	public static void readData() throws IOException {
		DataInputStream dis = new DataInputStream(new FileInputStream("c:\\data.txt"));
		
		int num = dis.readInt();
		boolean b = dis.readBoolean();
		double d = dis.readDouble();
		
		//234,true,9887.543
		System.out.println(num+"," + b + "," + d);
		dis.close();
	}
	
	public static void writeUTF() throws IOException {
		DataOutputStream dos = new DataOutputStream(new FileOutputStream("c:\\data.txt"));
		dos.writeUTF("你好");
		dos.close();
		
		//传统方式
//		OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("c:\\data.txt"),"utf-8");
//		osw.write("你好");
//		osw.close();
	}
	
	public static void readUTF() throws IOException {
		DataInputStream dis = new DataInputStream(new FileInputStream("c:\\data.txt"));
		String s = dis.readUTF();
		
		System.out.println(s);
		dis.close();
	}
}