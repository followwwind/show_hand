package com.wind.client;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

import com.wind.model.SocketChannel;

public class ClientHandler implements Runnable{
	
	private SocketChannel socket;
	
	
	
	public ClientHandler(SocketChannel socket) {
		super();
		this.socket = socket;
	}



	@Override
	public void run() {
		try {
			BufferedReader in = socket.getInput();
			String message = null;
			while ((message = in.readLine()) != null) {
				System.out.println(message);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
