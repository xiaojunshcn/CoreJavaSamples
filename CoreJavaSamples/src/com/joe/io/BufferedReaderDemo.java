package com.joe.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * BufferedReader读文件内容
 * 
 * @author JoeXIAO
 *
 */
public class BufferedReaderDemo {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		FileReader fr = new FileReader("c:\\buf.txt");
		
		BufferedReader bufr = new BufferedReader(fr);
		
		String line = null;
		//读一行方法
		while((line = bufr.readLine()) != null) {
			System.out.println(line);
		}
		
		bufr.close();
	}

}
