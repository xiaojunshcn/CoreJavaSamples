package com.joe.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * ByteArrayInputStream/ByteArrayOutputStream 读写文件
 * 
 * @author JoeXIAO
 *
 */
public class ByteArrayStreamDemo {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException {
		//数据源
		ByteArrayInputStream bis = new ByteArrayInputStream("ABCDEF".getBytes());
		
		//数据目的
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		
		int by = 0;
		while ((by = bis.read()) != -1) {
			bos.write(by);
		}
		System.out.println(bos.size());	//6
		
		System.out.println(bos.toString());	//ABCDEF
		
		bos.writeTo(new FileOutputStream("c:\\data.txt"));
	}
}
