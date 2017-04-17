package com.joe.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * BufferedReader/BufferedWriter 复制文件
 * @author JoeXIAO
 *
 */
public class BufferedCopyTextFile {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BufferedReader bufr = null;
		BufferedWriter bufw = null;
		
		try {
			bufr = new BufferedReader(new FileReader("C:\\EmailData\\readme.txt"));
			bufw = new BufferedWriter(new FileWriter("c:\\haha.txt"));
			
			String line = null;
			
			while((line = bufr.readLine()) != null) {
				bufw.write(line);
				bufw.newLine();
				bufw.flush();
			}
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			if (bufr!=null) {
				try {
					bufr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if (bufw !=null) {
				try {
					bufw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}