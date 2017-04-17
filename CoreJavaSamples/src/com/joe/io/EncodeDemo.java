package com.joe.io;

import java.util.Arrays;

/**
 * 同一字符串不同编码效果
 * 
 * @author JoeXIAO
 *
 */
public class EncodeDemo {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		//sample1();
		
		sample2();
	}

	public static void sample2() throws Exception  {
		String s = "联通";
		byte[] by = s.getBytes("gbk");
		
		for(byte b: by) {
			//&255 取最后8位
//			11000001
//			10101010
//			11001101
//			10101000
			System.out.println(Integer.toBinaryString(b&255));
		}
	}
	
	public static void sample1() throws Exception {
		String s = "你好";
		
		byte[] b1 = s.getBytes();
		//[-28, -67, -96, -27, -91, -67]
		System.out.println(Arrays.toString(b1));
		String s1 = new String(b1,"UTF-8");
		System.out.println(s1);	//你好
		
		b1 = s.getBytes("GBK");
		//[-60, -29, -70, -61]
		System.out.println(Arrays.toString(b1));
		s1 = new String(b1,"GBK");
		System.out.println(s1);	//你好
		
		b1 = s.getBytes("UTF-8");
		//[-28, -67, -96, -27, -91, -67]
		System.out.println(Arrays.toString(b1));
		s1 = new String(b1,"GBK");
		System.out.println(s1);	//浣犲ソ
		
		//乱码时，再进行一次编码
		byte[] b2 = s1.getBytes("GBK");
		String s2 = new String(b2,"UTF-8");
		System.out.println(s2);	//你好,又正常了
	}
}
