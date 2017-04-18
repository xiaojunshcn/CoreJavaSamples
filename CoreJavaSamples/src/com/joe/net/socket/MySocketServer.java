package com.joe.net.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MySocketServer {
	private int port = 8000;
	private ServerSocket serverSocket;
	public MySocketServer() throws IOException {
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
				System.out.println("New connection accepted "
						+ socket.getInetAddress() + ":" + socket.getPort());
				
				//receive/send data
			} catch (IOException e) {
				//when exception occurred, this scenario do not want to terminate the loop.
				//maybe it is just caused by one client side
				e.printStackTrace();
			} finally {
				try {
					if (socket != null)
						socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} //end of while
	}

	public static void main(String[] args) throws Exception {
		MySocketServer server = new MySocketServer();
		//sleep 10 minutes
		//Thread.sleep(60000 * 10);
		server.service();  
	}
}