package com.joe.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 对象序列化到文件。
 * 序列化后的文件再被读取到对象里。
 *
 */
public class ObjectStreamDemo {

	public static void main(String[] args) throws Exception {
		//writeObject();
		
		readObject();
	}

	/**
	 * 把一个对象写到文件里
	 * 
	 * @throws IOException
	 */
	public static void writeObject() throws IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("c:\\obj.txt"));
		
		oos.writeObject(new Person("lisi",39));
		oos.close();
		
	}
	
	/**
	 * 把序列化后的数据重新写到对象里
	 * 
	 * @throws Exception
	 */
	public static void readObject() throws Exception {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("c:\\obj.txt"));
		
		Object o = ois.readObject();
		
		if (o instanceof Person) {
			//name:lisi,age:39
			System.out.println((Person)o);
		}
		ois.close();
		
	}
}

//如果某个成员变量不希望被序列化，可以在前面加上关键字：transient
class Person implements Serializable{
	String name;
	int age;
	
	Person(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	public String toString() {
		return "name:" + name + ",age:" + age;
	}
}