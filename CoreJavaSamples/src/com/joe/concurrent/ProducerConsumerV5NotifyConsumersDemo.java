package com.joe.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 多个生产者线程来生产
 * 多个消费者线程负责消费
 * 
 * 生产出一个就消费掉一个
 * 
 * jdk 5方式, 唤醒对立方的等待线程。
 * 
 */
public class ProducerConsumerV5NotifyConsumersDemo {

	public static void main(String[] args) {
		Resource3 r = new Resource3();
		
		Producer3 pro = new Producer3(r);
		Consumer3 con = new Consumer3(r);
		
		Thread t1 = new Thread(pro);
		Thread t2 = new Thread(pro);
		
		Thread t3 = new Thread(con);
		Thread t4 = new Thread(con);
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
	}

}
//Thread-2...消费者.....商品--1045
//Thread-0...生产者..商品--1046
//Thread-2...消费者.....商品--1046
//Thread-0...生产者..商品--1047
//Thread-2...消费者.....商品--1047
//Thread-0...生产者..商品--1048
//Thread-2...消费者.....商品--1048
//Thread-0...生产者..商品--1049
//Thread-2...消费者.....商品--1049
//Thread-0...生产者..商品--1050
//Thread-2...消费者.....商品--1050
//Thread-0...生产者..商品--1051
//Thread-2...消费者.....商品--1051
class Resource3 {
	private String name;
	private int count = 1;
	private boolean flag = false;
	
	private Lock lock = new ReentrantLock();
	private Condition con_pro = lock.newCondition();
	private Condition con_con = lock.newCondition();
	
	//for producer
	public void set(String name) throws InterruptedException{
		lock.lock();	//拿到锁
		
		try{
			while(flag) {
				con_pro.await();
			}
			
			this.name = name + "--" + count++;
			System.out.println(Thread.currentThread().getName()+"...生产者.." + this.name);
			flag = true;
			
			con_con.signalAll();	//唤醒consumer线程
		} finally {
			lock.unlock();	//释放锁
		}
	}
	
	//for consumer
	public void out() throws InterruptedException{
		lock.lock();
		try {
			while(!flag) {
				con_con.await();
			}
			System.out.println(Thread.currentThread().getName()+"...消费者....." + this.name);
			flag = false;
			con_pro.signalAll();	//唤醒producer线程
		} finally {
			lock.unlock();	//释放锁
		}
	}
}

class Producer3 implements Runnable {
	private Resource3 res;
	Producer3(Resource3 res) {
		this.res = res;
	}
	
	public void run() {
		while (true) {
			try {
				res.set("商品");
			} catch(InterruptedException e) {
				
			}
		}
	}
}

class Consumer3 implements Runnable {
	private Resource3 res;
	Consumer3(Resource3 res) {
		this.res = res;
	}
	
	public void run() {
		while (true) {
			try {
				res.out();
			} catch (InterruptedException e) {
			}
		}
	}
}