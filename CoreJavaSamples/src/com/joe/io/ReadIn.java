package com.joe.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 * 需求：
 * 通过键盘录入数据
 * 当录入一行数据后，就将该行数据进行打印
 * 如果录入的数据是over，那么停止录入
 * 
 */
public class ReadIn {

	public static void main(String[] args) throws IOException {
		//read_1();
		
		//read_line();
		
		read_line_save_to_file();
		
		//read_file_to_console();
	}

	public static void read_1() throws IOException {
		InputStream in = System.in;
		
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			int ch = in.read();
			if (ch=='\r')
				continue;
			if (ch == '\n') {
				String s = sb.toString();
				if ("over".equals(s)) {
					break;
				}
				
				System.out.println(s.toUpperCase());
				
				//清空缓冲区
				sb.delete(0, sb.length());
			} else 
				sb.append((char)ch);
			
		}
	}
	
	//把字节流转成字符流，再用字符流的readLine()方法
	//InputStreamReader		是字节流通向字符流的桥梁
	//OutputStreamWriter	字符流通向字节流的桥梁
	public static void read_line() throws IOException {
		//1. 获取键盘录入对象		System.in;
		//2. 将字节流对象转换成字节流对象
		//3. 缓冲区技术
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		OutputStream out = System.out;	//目的---〉控制台
		OutputStreamWriter osw = new OutputStreamWriter(out);
		BufferedWriter bufw = new BufferedWriter(osw);
		
		String line = null;
		while((line = in.readLine()) != null) {
			if ("over".equals(line)) {
				break;
			}
			//System.out.println(line.toUpperCase());
			
			bufw.write(line.toUpperCase());
			bufw.newLine();
			bufw.flush();
		}
		in.close();
		out.close();
	}
	
	//把键盘输入存储到文件
	public static void read_line_save_to_file() throws IOException {
		//1. 获取键盘录入对象		System.in;
		//2. 将字节流对象转换成字节流对象
		//3. 缓冲区技术
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		OutputStream out = new FileOutputStream("c:\\out2.txt");
		OutputStreamWriter osw = new OutputStreamWriter(out,"GBK");
		BufferedWriter bufw = new BufferedWriter(osw);
		
		String line = null;
		while((line = in.readLine()) != null) {
			if ("over".equals(line)) {
				break;
			}
			//System.out.println(line.toUpperCase());
			
			bufw.write(line.toUpperCase());
			bufw.newLine();
			bufw.flush();
		}
		in.close();
		out.close();
	}
	
	//读文件到控制台
	public static void read_file_to_console() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("c:\\s1.txt"),"GBK"));
		
		OutputStream out = System.out;
		OutputStreamWriter osw = new OutputStreamWriter(out,"UTF-8");
		BufferedWriter bufw = new BufferedWriter(osw);
		
		String line = null;
		while((line = in.readLine()) != null) {
			if ("over".equals(line)) {
				break;
			}
			//System.out.println(line.toUpperCase());
			
			bufw.write(line.toUpperCase());
			bufw.newLine();
			bufw.flush();
		}
		in.close();
		out.close();
	}
}