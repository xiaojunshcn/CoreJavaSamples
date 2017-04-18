package com.joe.net.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MySocketServerMultiThread {
	private int port = 8000;
	private ServerSocket serverSocket;
	public MySocketServerMultiThread() throws IOException {
		//the maximum length of the queue is 3
		serverSocket = new ServerSocket(port, 3);
		System.out.println("Server started...");
	}

	public void service() {
		while (true) {
			Socket socket = null;
			try {
				//get one connection from the requesting queue of connections
				socket = serverSocket.accept();
				//receive/send data in each thread
				Thread workThread=new Thread(new Handler(socket));
				workThread.start();

			} catch (IOException e) {
				//when exception occurred, this scenario do not want to terminate the loop.
				//maybe it is just caused by one client side
				e.printStackTrace();
			}
		} //end of while
	}

	public static void main(String[] args) throws Exception {
		MySocketServerMultiThread server = new MySocketServerMultiThread();
		//sleep 10 minutes
		//Thread.sleep(60000 * 10);
		server.service();  
	}
} //end of class MySocketServerMultiThread

// in charge of communicating for one client
class Handler implements Runnable {
	private Socket socket;
	public Handler(Socket socket) {
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