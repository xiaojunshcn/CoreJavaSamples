package com.joe.type;

public class JavaTypeTest {

	public static void main(String[] args) {
		System.out.println(3*0.1);	//0.30000000000000004
		
		JavaTypeTest o = new JavaTypeTest();
		o.byteToIntTest();
		o.printJvmVersion();
		o.printMemory();
		o.autoUnboxing();
		o.stringCompare();
	}

	private void byteToIntTest() {
		byte a = 127;
		byte b = 127;
		
		// error : cannot convert from int to byte
		//a+b 操作会将 a、b 提升为 int 类型，所以将 int 类型赋值给 byte 就会编译出错
		//b = a + b; 
		
		b += a; // ok
	}
	
	private void printJvmVersion() {
		//通过 Java 程序来判断 JVM 是 32 位 还是 64 位
		String jvmVersion = System.getProperty("os.arch");
		System.out.println(jvmVersion);	//amd64
		
		jvmVersion = System.getProperty("sun.arch.data.model");
		System.out.println(jvmVersion);	//64
	}
	
	private void printMemory() {
		//怎么获取 Java 程序使用的内存？堆使用的百分比？
		//可以通过 java.lang.Runtime 类中与内存相关方法来获取剩余的内存，总内存及最大堆内存。
		
		System.out.println(Runtime.getRuntime().freeMemory());	//123480280		返回剩余空间的字节数
		System.out.println(Runtime.getRuntime().totalMemory());	//124780544		返回总内存的字节数
		System.out.println(Runtime.getRuntime().maxMemory());	//1836580864	返回最大内存的字节数
	}
	
	/**
	 * 自动装箱
	 */
	private void autoUnboxing() {
		Integer a = new Integer(3);
        Integer b = 3;                  // 将3自动装箱成Integer类型
        int c = 3;
        System.out.println(a == b);     // false 两个引用没有引用同一对象
        System.out.println(a == c);     // true a自动拆箱成int类型再和c比较
        
        //首先需要注意的是f1、f2、f3、f4四个变量都是Integer对象引用，所以下面的==运算比较的不是值而是引用。
        Integer f1 = 100, f2 = 100, f3 = 150, f4 = 150;
        System.out.println(f1 == f2);	//true
        System.out.println(f3 == f4);	//false 重要 TODO
        //简单的说，如果整型字面量的值在-128到127之间，那么不会new新的Integer对象，而是直接引用常量池中的Integer对象
	}
	
	/**
	 * 字符串比较
	 * 
	 */
	private void stringCompare() {
		String s1 = "Programming";
        String s2 = new String("Programming");
        String s3 = "Program" + "ming";
        System.out.println(s1 == s2);	//false
        System.out.println(s1 == s3);	//true	重要 TODO
        //String对象的intern方法会得到字符串对象在常量池中对应的版本的引用（如果常量池中有一个字符串与String对象的equals结果是true），
        //如果常量池中没有对应的字符串，则该字符串将被添加到常量池中，然后返回常量池中字符串的引用。
        System.out.println(s1 == s1.intern());	//true
	}
}
