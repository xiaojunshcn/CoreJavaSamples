package com.joe.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

public class FileCopyStreamDemo {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		//writeTextWithCharacterSet();
		//readTextWithCharacterSet();
		copyFileWithCharacterSet();
	}

	public static void copyFileWithCharacterSet() throws IOException {

		/*
		此方式不能解决乱码问题
		FileWriter fw = new FileWriter("c:\\demo_copy.txt");
		FileReader fr = new FileReader("c:\\demo.txt");
		
//		此方式太慢，读一个写一个字符
//		int ch = 0;
//		while((ch=fr.read()) != -1) {
//			fw.write(ch);
//		}
		
		char[] buf = new char[1024];
		int len = 0;
		while((len=fr.read(buf)) != -1) {
			fw.write(buf,0,len);
		}
		
		fw.close();
		fr.close();*/
		
		OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("c:\\demo_copy.txt"),"UTF-8");
		InputStreamReader isr = new InputStreamReader(new FileInputStream("c:\\demo.txt"),"UTF-8");
		char[] buf = new char[1024];
		int len = 0;
		while ((len = isr.read(buf)) != -1) {
			osw.write(buf,0,len);
		}
		osw.close();
		isr.close();
	}
	
	
	public static void writeTextWithCharacterSet() {
		
		OutputStreamWriter osw = null;
		
			try {
				osw = new OutputStreamWriter(new FileOutputStream("c:\\demo.txt"),"UTF-8");
				osw.write("你好");	//6 bytes
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (osw != null) {
					try {
						osw.close();
					} catch (IOException e) {
						e.printStackTrace();
					}			
				}
			}
		
		
	}
	
	public static void readTextWithCharacterSet() {
		InputStreamReader isr = null;
		try {
			isr = new InputStreamReader(new FileInputStream("c:\\demo.txt"),"UTF-8");
			
			char[] buf = new char[1024];
			int len = isr.read(buf);
			
			String str = new String(buf,0,len);
			System.out.print(str);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (isr != null) {
				try {
					isr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}	
			}
		}
		
	}
}