package com.joe.io;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileReaderDemo {

	public static void main(String[] args) {
		FileReader fr = null;
		
		try {
			//创建一个文件读取流对象，和指定名称的文件相关联
			//要保证该文件是已经存在的，否则报FileNotFoundException
			fr = new FileReader("c:\\demo.txt");
			
			/*
			while(true) {
				//read()一次读一个字符,读到末尾返回：-1
				int ch = fr.read();	//可能抛IOException
				
				if (ch == -1)
					break;
				System.out.println("ch:" + (char)ch);
			}
			*/
			
			int ch = 0;
			//read()一次读一个字符,读到末尾返回：-1
			while((ch=fr.read()) != -1) {
				System.out.println("ch:" + (char)ch);
			}
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fr != null) {
					fr.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}