package com.joe.io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * BufferedWriter写入到文件中
 * 
 * @author JoeXIAO
 *
 */
public class BufferedWriterDemo {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		//创建一个字符写入流对象
		FileWriter fw = new FileWriter("c:\\buf.txt");
		
		//为了提高字符写入流效率。加入了缓冲技术
		//只要将需要被提高效率的流对象作为参数传递给缓冲区的构造函数即可
		BufferedWriter bufw = new BufferedWriter(fw);
		
		bufw.write("abcde");
		
		//记住，只要用到缓冲区，就要记得刷新
		bufw.flush();
		
		//其实关闭缓冲区，就是在关闭缓冲区中的流对象
		bufw.close();
		
		//可以不写此行
		fw.close();
	}

}
