package com.joe.net.socket;

import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class MySocketServerWithShutDown {
	private int port = 8000;
	private ServerSocket serverSocket;
	// thread pool
	private ExecutorService executorService;
	// thread pool size for a single CPU
	private final int POOL_SIZE = 4;

	// port for listening admin command
	private int portForShutdown = 8001;
	private ServerSocket serverSocketForShutdown;
	private boolean isShutdown = false;

	private Thread shutdownThread = new Thread() {
		public void start() {
			// set as daemon thread, also called backend thread
			this.setDaemon(true);
			super.start();
		}

		public void run() {
			while (!isShutdown) {
				Socket socketForShutdown = null;
				try {
					socketForShutdown = serverSocketForShutdown.accept();
					BufferedReader br = new BufferedReader(
							new InputStreamReader(
									socketForShutdown.getInputStream()));
					String command = br.readLine();
					if (command.equals("shutdown")) {
						long beginTime = System.currentTimeMillis();
						socketForShutdown.getOutputStream().write(
								"Server is shutting down\r\n".getBytes());
						isShutdown = true;
						// thread pool will not accept new tasks, but it will continue to do the task in work queue
						executorService.shutdown();

						// wait 30 s to close thread pool
						while (!executorService.isTerminated()) {
							executorService.awaitTermination(30,
									TimeUnit.SECONDS);
						}
						// close the socket connection
						serverSocket.close();
						long endTime = System.currentTimeMillis();
						socketForShutdown.getOutputStream().write(
								("Server was shutdown." + " Closing server costs " + (endTime - beginTime) + "ms. \r\n").getBytes());
						socketForShutdown.close();
						serverSocketForShutdown.close();

					} else {
						socketForShutdown.getOutputStream().write(
								"error command.\r\n".getBytes());
						socketForShutdown.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}; // end of shutdownThread

	public MySocketServerWithShutDown() throws IOException {
		serverSocket = new ServerSocket(port);
		// connection timeout for a client is 60s
		serverSocket.setSoTimeout(60000);
		serverSocketForShutdown = new ServerSocket(portForShutdown);
		// thread pool
		executorService = Executors.newFixedThreadPool(Runtime.getRuntime()
				.availableProcessors() * POOL_SIZE);

		// start the thread that will close the thread pool
		shutdownThread.start();
		System.out.println("server started.");
	}

	public void service() {
		while (!isShutdown) {
			Socket socket = null;
			try {
				socket = serverSocket.accept();
				// may throw a SocketTimeoutException和SocketException
				socket.setSoTimeout(60000);
				executorService.execute(new Handler4(socket));
				// may throw RejectedExecutionException
			} catch (SocketTimeoutException e) {
				// no need to do when client connection timeout
			} catch (RejectedExecutionException e) {
				try {
					if (socket != null)
						socket.close();
				} catch (IOException x) {
				}
				return;
			} catch (SocketException e) {
				// 如果是由于在执行serverSocket.accept()方法时，
				// ServerSocket被ShutdownThread线程关闭而导致的异常，就退出service()方法
				if (e.getMessage().indexOf("socket closed") != -1)
					return;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String args[]) throws IOException {
		new MySocketServerWithShutDown().service();
	}
}

//in charge of communicating for one client
class Handler4 implements Runnable {
	private Socket socket;
	public Handler4(Socket socket) {
		this.socket = socket;
	}
	private PrintWriter getWriter(Socket socket) throws IOException {
		//TODO
		return null;
	}
	private BufferedReader getReader(Socket socket) throws IOException {
		//TODO
		return null;
	}
	public String echo(String msg) {
		//TODO
		return msg;
	}
	
	public void run() {
		try {
			System.out.println("New connection accepted "
					+ socket.getInetAddress() + ":" + socket.getPort());
			BufferedReader br = getReader(socket);
			PrintWriter pw = getWriter(socket);

			String msg = null;
			if (br != null) {
				// receive and send data, till the communication is ended
				while ((msg = br.readLine()) != null) {
					System.out.println(msg);
					if (pw != null) {
						pw.println(echo(msg));
						if (msg.equals("bye"))
							break;
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				// close connection
				if (socket != null) {socket.close();}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
} //end of class Handler