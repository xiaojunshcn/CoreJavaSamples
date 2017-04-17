package com.joe.io;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MyBufferedStreamCopyMP3 {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		long start = System.currentTimeMillis();
		copy_2();
		long end = System.currentTimeMillis();
		System.out.println((end - start) + "毫秒");
	}

	public static void copy_2() throws IOException {
		//BufferedInputStream bufis0 = new BufferedInputStream(new FileInputStream("c:\\1.mp3"));
		//自定义的缓冲流
		MyBufferedInputStream bufis = new MyBufferedInputStream(new FileInputStream("c:\\1.mp3"));
		BufferedOutputStream bufos = new BufferedOutputStream(new FileOutputStream("c:\\2.mp3"));
		
		int by = 0;
		while((by = bufis.myRead()) != -1) {
			//write方法不会把前面补足的0写入到文件中。强制只写int数据里的最后一个字节的内容
			bufos.write(by);
		}
		
		bufos.close();
		bufis.myClose();
	}
}
