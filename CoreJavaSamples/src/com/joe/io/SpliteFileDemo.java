package com.joe.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.SequenceInputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;

/**
 * 按等分切割文件（一个读取流对应多个写文件流）
	并再合并它们	
	
	如果合并大的电影，需要再加个计数器。比如说计数到100，换一个新的文件，
	buf只能定义很小的几M。
	
 */
public class SpliteFileDemo {
	public static void main(String[] args) throws IOException {
		//spliteFile();
		
		mergeFiles();
	}
	
	public static void spliteFile() throws IOException {
		FileInputStream fis = new FileInputStream("c:\\mp31.mp3");
		FileOutputStream fos = null;
		
		byte[] buf = new byte[1024*1024];
		
		int len = 0;
		int count =1;
		
		while ((len = fis.read(buf)) != -1) {
			fos = new FileOutputStream("c:\\mp31_" + (count++) + ".part");
			
			fos.write(buf,0,len);
			
			fos.close();
		}
		
		if(fos != null) 
			fos.close();
		
		fis.close();
	}
	
	public static void mergeFiles() throws IOException {
		ArrayList<FileInputStream> al = new ArrayList<FileInputStream>();
		for (int x=1; x<=16;x++) {
			al.add(new FileInputStream("c:\\mp31_" + x + ".part"));
		}
		
		final Iterator<FileInputStream> it = al.iterator();
		
		Enumeration<FileInputStream> en = new Enumeration<FileInputStream>() {
			public boolean hasMoreElements() {
				return it.hasNext();
			}
			
			public FileInputStream nextElement() {
				return it.next();
			}

		};
		
		SequenceInputStream sis = new SequenceInputStream(en);
		FileOutputStream fos = new FileOutputStream("c:\\mp31_merged.mp3");
		
		byte[] buf = new byte[1024];
		int len = 0;
		
		while((len = sis.read(buf)) != -1) {
			fos.write(buf, 0, len);
		}
		
		fos.close();
		sis.close();
	}
}