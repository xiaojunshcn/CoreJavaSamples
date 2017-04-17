package com.joe.sort;

/**
 * 冒泡排序
 * 
 */
public class BubbleSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int a[] = { 1, 54, 6, 3, 78, 34, 12, 45 };

		int temp = 0;

		for (int i = 0; i < a.length-1; i++) {
			for (int j = 0; j < a.length-i-1; j++) {
				if (a[j] > a[j+1]) {
					temp = a[j];
					a[j] = a[j+1];
					a[j+1] = temp;
				}
			}
		}

		//1,3,6,12,34,45,54,78,
		for (int i = 0; i < a.length; i++)
			System.out.print(a[i]+",");
	}
}