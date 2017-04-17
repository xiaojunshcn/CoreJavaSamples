package com.joe.collection;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * 获取一串字符中的各字母出现次数
 * 
 * 将字符串转换成字符数组，因为要对每一个字母进行操作
 * 定义一个treeMap，因为打印结果的字母有顺序
 * 
 */
public class CountCharacterTimes {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String source = "abcaabdreaccrea"; 

		String s = charCount(source);
		//a(5)b(2)c(3)d(1)e(2)r(2)
		System.out.println(s);
	}
	
	public static String charCount(String str) {
		char[] chs = str.toCharArray();
		TreeMap<Character,Integer> tm = new TreeMap<Character,Integer>();
		
		for (int x=0; x<chs.length;x++) {
			if (!(chs[x] >='a' && chs[x]<='z')) {
				continue;
			} 
			
			Integer value = tm.get(chs[x]);
			
			if (value==null) {
				tm.put(chs[x], 1);
			} else {
				value = value + 1;
				tm.put(chs[x], value);
			}
		}
		
		//{a=5, b=2, c=3, d=1, e=2, r=2}
		//System.out.println(tm);
		
		StringBuilder sb = new StringBuilder();
		Set<Map.Entry<Character,Integer>> entrySet = tm.entrySet();
		
		Iterator<Map.Entry<Character,Integer>> it = entrySet.iterator();
		
		while(it.hasNext()) {
			Map.Entry<Character, Integer> me = it.next();
			Character ch = me.getKey();
			Integer value = me.getValue();
			sb.append(ch+"(" + value + ")");
		}
		
		return sb.toString();
	}

}
