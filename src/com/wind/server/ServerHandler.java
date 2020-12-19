package com.wind.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import com.alibaba.fastjson.JSON;
import com.wind.model.MsgModel;
import com.wind.model.SocketChannel;

public class ServerHandler  implements Runnable{
	
	// 当前线程交互的客户端的Socket
	private SocketChannel socket;

	public ServerHandler(SocketChannel socket) {
		this.socket = socket;
	}

	
	@Override
	public void run() {
		try {
			BufferedReader input = socket.getInput();
			String message = null;
			while ((message = input.readLine()) != null) {
				MsgModel model = JSON.parseObject(message, MsgModel.class);
				Integer type = model.getType();
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
