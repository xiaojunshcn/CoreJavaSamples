package com.joe.collection;

import java.util.Iterator;
import java.util.TreeSet;

/**
 * TreeSet本身有排序功能。
 * 
 * 如果要按自己的需求，比较年龄，需要实现 Comparable接口。
 * 接口里写具体逻辑
 * 
 *
 */
public class TreeSetDemoComparableObject {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TreeSet ts = new TreeSet();
		ts.add(new Student("lisi023",20));
		ts.add(new Student("lisi001",22));
		ts.add(new Student("lisi007",27));
		ts.add(new Student("lisi006",22));	
		
		Iterator<Student> it = ts.iterator();

//		lisi023--------->20
//		lisi001--------->22
//		lisi006--------->22
//		lisi007--------->27
		while (it.hasNext()) {
			Student s = it.next();
			System.out.println(s.getName() + "--------->" + s.getAge());
		}
	}

}

class Student implements Comparable{
	private String name;
	private int age;
	Student(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	public String getName() {
		return name;
	}
	public int getAge() {
		return age;
	}
	public int compareTo(Object obj) {
		if (!(obj instanceof Student)) {
			throw new RuntimeException("不是学生对象");
		}
		
		Student s = (Student) obj;
		if (this.age > s.age) return 1;
		if (this.age < s.age) return -1;
		
		if (this.age == s.age) {
			return this.name.compareTo(s.getName());
		}
		return 0;
	}
}