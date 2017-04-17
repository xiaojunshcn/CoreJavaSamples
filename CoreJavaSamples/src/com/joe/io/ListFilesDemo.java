package com.joe.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 带层次列出某一目录下的所有文件，文件夹。包括子目录
 * 
 */
public class ListFilesDemo {

	public static void main(String[] args) {
//		File dir = new File("C:\\Projects");
//		showDir(dir,0);
		
		//toBinary(10);	//1010
		
		
		saveFileNamesToAFile();
	}

	//10进制转成2进制
	public static void toBinary(int num) {
		if (num >0) {
			toBinary(num/2);
			System.out.print(num%2);
		}
	}
	
	public static String getLevel(int level) {
		StringBuilder sb = new StringBuilder();
		sb.append("|--");
		for (int x=0;x<level;x++) {
			sb.insert(0, "|  ");
		}
		return sb.toString();
	}
	
	public static void showDir(File dir, int level) {
		System.out.println(getLevel(level) + dir.getName());
		level++;
		
		File[] files = dir.listFiles();
		
		for (int x=0; x<files.length; x++) {
			if (files[x].isDirectory())
				showDir(files[x], level);
			else
				System.out.println(getLevel(level) + files[x]);
		}
	}
	
	//将指定目录下的java文件的绝对路径，存储到一个文本文件中。
	//建立一个java文件列表文件
	public static void saveFileNamesToAFile() {
		File dir = new File("C:\\Projects");
		
		List<File> list = new ArrayList<File>(); 
		fileToList(dir, list);
		System.out.println(list.size());
		
		writeToFile(list, "c:\\fileNames.txt");
	}
	
	public static void fileToList(File dir, List<File> list) {
		File[] files = dir.listFiles();
		
		for(File file:files) {
			if (file.isDirectory()) 
				fileToList(file,list);
			else {
				if (file.getName().endsWith(".java")) 
					list.add(file);
			}
		}
	}
	
	public static void writeToFile(List<File> list, String javaListFile) {
		BufferedWriter bufw = null;
		try {
			bufw = new BufferedWriter(new FileWriter(javaListFile));
			
			for(File f:list) {
				String path = f.getAbsolutePath();
				bufw.write(path);
				bufw.newLine();
				bufw.flush();
			}
		} catch(IOException e) {
			
		} finally {
			try{
				if (bufw != null) 
					bufw.close();
			} catch(IOException e) {
				
			}
		}
	}
}