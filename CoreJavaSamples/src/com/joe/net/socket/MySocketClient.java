package com.joe.net.socket;

import java.net.Socket;

public class MySocketClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		final int length = 100;
		String host = "localhost";
		int port = 8000;
		Socket[] sockets = new Socket[length];
		//try to create 100 connections
		for (int i = 0; i < length; i++) {
			sockets[i] = new Socket(host, port);
			System.out.println("Nbr of " + (i + 1) + " is connected successfully.");
		}
		Thread.sleep(3000);
		
		//close connections
		for (int i = 0; i < length; i++) {
			sockets[i].close();
		}

	}

}
