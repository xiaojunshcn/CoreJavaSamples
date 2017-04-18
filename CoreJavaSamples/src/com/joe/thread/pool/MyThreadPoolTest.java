package com.joe.thread.pool;

public class MyThreadPoolTest {

	public static void main(String[] args) {
		//tasks
		int numTasks = 5;
		//pool size
		int poolSize = 3;

		MyThreadPool threadPool = new MyThreadPool(poolSize);
		for (int i = 0; i < numTasks; i++) {
			threadPool.execute(createTask(i));
		}
		
		//waiting the thread to finish the task
		threadPool.join();
	}

	/** define a simple task: print id */
	private static Runnable createTask(final int taskID) {
		return new Runnable() {
			public void run() {
				System.out.println("Task " + taskID + ": start");
				try {
					//500ms to run the task
					Thread.sleep(500); 
				} catch (InterruptedException ex) {
				}
				System.out.println("Task " + taskID + ": end");
			}
		};
	}
}