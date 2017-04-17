package com.joe.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Properties是Hashtable的子类。
 * 
 * 用于记录应用程序次数。
 * 如果次数已到，给出注册提示。
 * 
 * 思路：维护一个配置文件,键值对形式的数据
 *
 */
public class PropertiesDemo {

	public static void main(String[] args) throws IOException {
		Properties prop = new Properties();
		
		File file = new File("c:\\count.ini");
		if (!file.exists())
			file.createNewFile();
		
		FileInputStream fis = new FileInputStream(file);
		
		prop.load(fis);	//流中的数据加载到集合中
		
		int count = 0;
		String value = prop.getProperty("times");
		if (value != null) {
			count = Integer.parseInt(value);
			
			if (count>=5) {
				System.out.println("trial ended!");
				return;
			}
		}
		
		count++;
		prop.setProperty("times", count+"");
		
		FileOutputStream fos = new FileOutputStream(file);
		
		prop.store(fos, "no comments");
		fos.close();
		fis.close();
	}
}