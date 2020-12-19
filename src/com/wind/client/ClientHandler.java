package com.wind.client;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientHandler implements Runnable{
	
	private Socket socket;
	
	
	
	public ClientHandler(Socket socket) {
		super();
		this.socket = socket;
	}



	@Override
	public void run() {
		try {
			InputStream in = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(in, "UTF-8");
			BufferedReader br = new BufferedReader(isr);
			String message = null;
			while ((message = br.readLine()) != null) {
				System.out.println(message);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
