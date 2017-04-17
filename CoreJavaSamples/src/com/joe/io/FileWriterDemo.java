package com.joe.io;

import java.io.FileWriter;
import java.io.IOException;

/**
 * 需求：在硬盘上创建一个文件并写入一些文字数据
 * 
 */
public class FileWriterDemo {

	public static void main(String[] args)  {
		FileWriter writer = null;
		
		try{
			//创建一个fileWriter对象,该对象一被初始化就必须要明确被操作的文件
			//该文件会被创建。如果有同名文件，将被此新的覆盖
			//第二个参数表示如果已经有此文件存在，那么在此文件的内容后再追加内容
			writer = new FileWriter("c:\\demo.txt",true);	
			
			writer.write("abcd");	//写入流中
			writer.write("haha\r\nnihao");		//加入换行符\r\n
			writer.flush();			//流中内容写到文件中
			
		} catch(IOException e) {
			System.out.println(e.toString());
		} finally {
			try{
				if (writer!= null) {
					//关闭流资源，但是关闭前会刷新一次内部的huan冲中的数据
					//和flush区别：flush刷新后，流可继续使用。
					writer.close();
				}
			}catch(IOException e) {
				System.out.println(e.toString());
			}
		}
	}
}