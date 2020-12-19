package com.wind.client;

import java.io.BufferedWriter;
import java.io.IOException;
import java.net.Socket;

import com.alibaba.fastjson.JSON;
import com.wind.config.Const;
import com.wind.model.MsgModel;
import com.wind.model.SocketChannel;

public class Client {

	// 用于与服务端通信的Socket
	private SocketChannel socket;
	
	private String ip;
	/**
	 * 构造方法，用来初始化客户端
	 * 
	 * @throws Exception
	 */
	public Client(String ip) throws Exception {
		try {
			/*
			 * 初始化Socket的同时要指定服务端的 IP地址与端口号 IP地址用于我们在网络上找到服务端所在 的计算机。
			 * 端口号用于找到服务器上的服务端应用 程序。
			 * 
			 * 实例化Socket的过程就是连接服务端的 过程，若服务端无响应，这里的构造方法 会抛出异常。
			 */
			System.out.println("正在连接服务端...");
			this.ip = ip;
			this.socket = new SocketChannel(new Socket(ip, Const.PORT));
			System.out.println("与服务端连接完毕！");
		} catch (Exception e) {
			System.out.println("初始化失败!");
			throw e;
		}
	}
	
	/**
	 * 发送消息
	 * @param model
	 */
	public boolean sendMsg(MsgModel model) {
		if(model == null) {
			return false;
		}
		BufferedWriter output = socket.getOutput();
		try {
			output.write(JSON.toJSONString(model));
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	/**
	 * 客户端开始工作的方法
	 * 
	 * @throws Exception
	 */
	public void start() throws Exception {
		try {
			// 启动用于读取服务端发送消息的线程
			ClientHandler handler = new ClientHandler(this.socket);
			Thread t = new Thread(handler);
			t.start();
		} catch (Exception e) {
			System.out.println("客户端运行失败!");
			throw e;
		}
	}
	
	

	public SocketChannel getSocket() {
		return socket;
	}

	public void setSocket(SocketChannel socket) {
		this.socket = socket;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public static void main(String[] args) {
		try {
			Client client = new Client(Const.IP);
			client.start();
		} catch (Exception e) {
			System.out.println("客户端运行失败!");
			e.printStackTrace();
		}
	}
}
