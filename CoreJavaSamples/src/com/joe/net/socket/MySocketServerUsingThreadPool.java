package com.joe.net.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import com.joe.thread.pool.MyThreadPool;

public class MySocketServerUsingThreadPool {
	private int port = 8000;
	private ServerSocket serverSocket;
	private MyThreadPool threadPool;
	
	//working thread in pool for a single cpu
	private final int POOL_SIZE=4;
	
	public MySocketServerUsingThreadPool() throws IOException {
		serverSocket = new ServerSocket(port);
		
		//create thread pool.
		//Runtime.availableProcessors() returns number of CPUs for current system
		threadPool= new MyThreadPool(  
				Runtime.getRuntime().availableProcessors() * POOL_SIZE);  

		System.out.println("Server started...");
	}

	public void service() {
		while (true) {
			Socket socket = null;
			try {
				//get one connection from the requesting queue of connections
				socket = serverSocket.accept();
				//receive/send data in each thread
				threadPool.execute(new Handler2(socket));
			} catch (IOException e) {
				//when exception occurred, this scenario do not want to terminate the loop.
				//maybe it is just caused by one client side
				e.printStackTrace();
			}
		} //end of while
	}

	public static void main(String[] args) throws Exception {
		MySocketServerUsingThreadPool server = new MySocketServerUsingThreadPool();
		//sleep 10 minutes
		//Thread.sleep(60000 * 10);
		server.service();  
	}
} //end of class MySocketServerUsingThreadPool

// in charge of communicating for one client
class Handler2 implements Runnable {
	private Socket socket;
	public Handler2(Socket socket) {
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