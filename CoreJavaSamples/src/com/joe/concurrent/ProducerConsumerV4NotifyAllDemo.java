package com.joe.concurrent;

/**
 * 多个生产者线程来生产
 * 多个消费者线程负责消费
 * 
 * 生产出一个就消费掉一个
 * 
 * jdk 1.4方式
 *
 */
public class ProducerConsumerV4NotifyAllDemo {

	public static void main(String[] args) {
		Resource r = new Resource();
		
		Producer pro = new Producer(r);
		Consumer con = new Consumer(r);
		
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
//Thread-1...生产者..商品--4789
//Thread-3...消费者.....商品--4789
//Thread-0...生产者..商品--4790
//Thread-3...消费者.....商品--4790
//Thread-1...生产者..商品--4791
//Thread-2...消费者.....商品--4791
//Thread-1...生产者..商品--4792
//Thread-3...消费者.....商品--4792
//Thread-0...生产者..商品--4793
class Resource {
	private String name;
	private int count = 1;
	private boolean flag = false;
	
	//for producer
	public synchronized void set(String name) {
		while(flag) {
			try{wait();} catch(Exception e) {}
		}
		
		this.name = name + "--" + count++;
		System.out.println(Thread.currentThread().getName()+"...生产者.." + this.name);
		flag = true;
		notifyAll();
	}
	
	//for consumer
	public synchronized void out() {
		while(!flag) {
			try{wait();} catch(Exception e) {}
		}
		System.out.println(Thread.currentThread().getName()+"...消费者....." + this.name);
		flag = false;
		notifyAll();
	}
}

class Producer implements Runnable {
	private Resource res;
	Producer(Resource res) {
		this.res = res;
	}
	
	public void run() {
		while (true) {
			res.set("商品");
		}
	}
}

class Consumer implements Runnable {
	private Resource res;
	Consumer(Resource res) {
		this.res = res;
	}
	
	public void run() {
		while (true) {
			res.out();
		}
	}
}