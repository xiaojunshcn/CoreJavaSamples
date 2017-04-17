package com.joe.collection;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * TreeSet本身有排序功能。
 * 
 * 如果要按自己的需求，比较姓名，需要在TreeSet的构造器里加 Comparator接口实例。
 * 接口里写具体逻辑
 * 
 *
 */
public class TreeSetDemoMyCompareObject {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TreeSet ts = new TreeSet(new MyCompare());
		ts.add(new Student2("lisi023",20));
		ts.add(new Student2("lisi001",22));
		ts.add(new Student2("lisi007",27));
		ts.add(new Student2("lisi006",22));	
		
		Iterator<Student2> it = ts.iterator();

//		lisi001--------->22
//		lisi006--------->22
//		lisi007--------->27
//		lisi023--------->20
		while (it.hasNext()) {
			Student2 s = it.next();
			System.out.println(s.getName() + "--------->" + s.getAge());
		}
	}

}

class Student2 {
	private String name;
	private int age;
	Student2(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	public String getName() {
		return name;
	}
	public int getAge() {
		return age;
	}
}

class MyCompare implements Comparator {

	@Override
	public int compare(Object obj1, Object obj2) {
		Student2 s1 = (Student2)obj1;
		Student2 s2 = (Student2)obj2;
		
		return s1.getName().compareTo(s2.getName());
	}
}