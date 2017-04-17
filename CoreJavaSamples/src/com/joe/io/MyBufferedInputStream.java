package com.joe.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * 自己写缓冲类
 *
 */
public class MyBufferedInputStream {
	private InputStream in;
	//4k
	private byte[] buf = new byte[1024*4];
	
	private int pos = 0;
	private int count = 0;
	 
	MyBufferedInputStream(InputStream in) {
		this.in = in;
	}
	
	//一次读一个字节，从缓冲区（字节数组）获取
	public int myRead() throws IOException {
		//通过in对象读取硬盘上数据，并存储buf中
		if(count ==0) {
			count = in.read(buf);
			if(count<0)
				return -1;
			
			pos = 0;
			byte b = buf[pos];
			
			count--;
			pos++;

			//因为返回是int型，四个byte，所以要和255取逻辑与，确保byte转换时没有数据实真
			return b&255;
		} else if (count >0) {
			byte b = buf[pos];
			
			count--;
			pos++;
			
			return b&0xff;
		}
		
		return -1;
	}
	
	public void myClose() throws IOException {
		in.close();
	}
}