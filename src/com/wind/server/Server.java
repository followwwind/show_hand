package com.wind.server;

import java.net.ServerSocket;
import java.net.Socket;

import com.wind.config.Const;
import com.wind.model.SocketChannel;

/**
 * 服务器
 * @author follow
 *
 */
public class Server {
	
	private ServerSocket server;
	
	/**
	 * 构造方法，用来初始化服务端
	 * 
	 * @throws Exception
	 */
	public Server() throws Exception {
		try {
			/*
			 * 实例化ServerSocket的同时，指定服务端口 客户端就是通过该端口连接上服务端的
			 */
			server = new ServerSocket(Const.PORT);
		} catch (Exception e) {
			System.out.println("服务端初始化失败!");
			throw e;
		}
	}
	
	/**
	 * 服务端开始工作的方法
	 * 
	 * @throws Exception
	 */
	public void start() throws Exception {
		try {
			System.out.println("等待客户端连接...");
			/*
			 * ServerSocket提供了一个方法: Socket accept() 该方法是一个阻塞方法，用于监听其打开的
			 * 8088端口，当一个客户端通过该端口与 服务端连接时，accept方法就会解除阻塞 然后创建一个Socket实例并返回。这个
			 * Socket的作用就是与刚刚连上的客户端进行 通讯。
			 */
			while (true) {
				Socket socket = server.accept();
				SocketChannel channel = new SocketChannel(socket);
				CacheManager.CHANNEL_LIST.add(channel);
				System.out.println("一个客户端连接了!");
				// 启动一个线程来处理该客户端的交互工作
				ServerHandler handler = new ServerHandler(channel);
				Thread t = new Thread(handler);
				t.start();
			}

		} catch (Exception e) {
			System.out.println("服务端运行失败!");
			throw e;
		}
	}
	
	public static void main(String[] args) {
		try {
			Server server = new Server();
			server.start();
		} catch (Exception e) {
			System.out.println("服务端启动失败!");
			e.printStackTrace();
		}
	}
}
