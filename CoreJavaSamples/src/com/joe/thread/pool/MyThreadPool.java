package com.joe.thread.pool;

import java.util.LinkedList;

public class MyThreadPool extends ThreadGroup {
	//thread pool is closed?
	private boolean isClosed=false;

	//work queue of threads
	private LinkedList<Runnable> workTasksQueue;
	
	private static int threadPoolID;
	//working thread id
	private int threadId;
	
	public MyThreadPool(int poolSize) {
		super("ThreadPool-" + (threadPoolID++));  
		setDaemon(true);  
		//create working queue of tasks
		workTasksQueue = new LinkedList<Runnable>(); 
		
		for (int i=0; i<poolSize; i++)  {
			//create a thread and run it
			new WorkThread().start();
		}
	}
	
	/** add a new task to the working queue, and let the working thread to do the task */
	public synchronized void execute(Runnable task) {
		// check is it closed
		if (isClosed) {
			throw new IllegalStateException();
		}
		if (task != null) {
			workTasksQueue.add(task);
			// notify waiting thread
			notify();
		}
	}

	/** get one task from work queue */
	protected synchronized Runnable getTask() throws InterruptedException {
		while (workTasksQueue.size() == 0) {
			if (isClosed)
				return null;
			// when no task in working queue, just waiting
			wait();
		}
		return workTasksQueue.removeFirst();
	}

	/** close thread pool */
	public synchronized void close() {
		if (!isClosed) {
			isClosed = true;
			// clear tasks
			workTasksQueue.clear();
			// stop all threads, this method extends from ThreadGroup
			interrupt();
		}
	} 

	/** waiting the thread to finish the task*/
	public void join() {
		synchronized (this) {
			isClosed = true;
			// notify all threads which are waiting tasks in getTask()
			notifyAll();
		}
		Thread[] threads = new Thread[activeCount()];
		// enumerate() extends from ThreadGroup class: get the counts of threads
		// that are available in ThreadGroup
		int count = enumerate(threads);
		for (int i = 0; i < count; i++) {
			try {
				//wait for this thread to die
				threads[i].join();
			} catch (InterruptedException ex) {
			}
		}
	}

	/** inner class: working thread */
	private class WorkThread extends Thread {
		public WorkThread() {
			// join to current ThreadPool group
			super(MyThreadPool.this, "WorkThread-" + (threadId++));
		}

		public void run() {
			// isInterrupted() extends from Thread: checking whether this thread
			// is interrupted
			while (!isInterrupted()) {
				Runnable task = null;
				try {
					// get the task
					task = getTask();
				} catch (InterruptedException ex) {
				}

				// when getTask() returns null or thread is interrupted in
				// getTask(), then end this thread
				if (task == null)
					return;

				try {
					task.run();
				} catch (Throwable t) {
					t.printStackTrace();
				}

			} // end of checking isInterrupted()
		} // end of run
	} // end of workThread class
} // end of JoeThreadPool class