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
 * jdk 5方式，唤醒所有线程。包括自己一方的线程
 *
 */
public class ProducerConsumerV5NotifyAllDemo {

	public static void main(String[] args) {
		Resource2 r = new Resource2();
		
		Producer2 pro = new Producer2(r);
		Consumer2 con = new Consumer2(r);
		
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
//Thread-1...生产者..商品--1670
//Thread-3...消费者.....商品--1670
//Thread-0...生产者..商品--1671
//Thread-2...消费者.....商品--1671
//Thread-1...生产者..商品--1672
//Thread-3...消费者.....商品--1672
//Thread-0...生产者..商品--1673
//Thread-2...消费者.....商品--1673
//Thread-1...生产者..商品--1674
//Thread-3...消费者.....商品--1674
class Resource2 {
	private String name;
	private int count = 1;
	private boolean flag = false;
	
	private Lock lock = new ReentrantLock();
	private Condition con = lock.newCondition();

	
	//for producer
	public void set(String name) throws InterruptedException{
		lock.lock();	//拿到锁
		
		try{
			while(flag) {
				con.await();
			}
			
			this.name = name + "--" + count++;
			System.out.println(Thread.currentThread().getName()+"...生产者.." + this.name);
			flag = true;
			
			con.signalAll();	//唤醒所有
		} finally {
			lock.unlock();	//释放锁
		}
	}
	
	//for consumer
	public void out() throws InterruptedException{
		lock.lock();
		try {
			while(!flag) {
				con.await();
			}
			System.out.println(Thread.currentThread().getName()+"...消费者....." + this.name);
			flag = false;
			con.signalAll();	//唤醒
		} finally {
			lock.unlock();	//释放锁
		}
	}
}

class Producer2 implements Runnable {
	private Resource2 res;
	Producer2(Resource2 res) {
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

class Consumer2 implements Runnable {
	private Resource2 res;
	Consumer2(Resource2 res) {
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