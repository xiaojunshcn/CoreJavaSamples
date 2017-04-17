package com.joe.io;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * buffer方式读文件的内容
 */
public class FileReaderDemo2 {

	public static void main(String[] args) {
		FileReader fr = null;
		
		try {
			//创建一个文件读取流对象，和指定名称的文件相关联
			//要保证该文件是已经存在的，否则报FileNotFoundException
			fr = new FileReader("c:\\demo.txt");
			
			//定义一个字符数组，用于存储读到的字符
			char[] buf = new char[1024];	//通常定义1024的整数倍
			
			int num = 0;
			//fr.read(buf)表示读到了字符的个数
			while ((num = fr.read(buf)) != -1) {
				System.out.println(new String(buf, 0, num));
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