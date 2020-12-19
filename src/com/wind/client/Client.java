package com.wind.client;

import java.net.Socket;

import com.wind.config.Const;

public class Client {

	// 用于与服务端通信的Socket
	private Socket socket;
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
			this.socket = new Socket(ip, Const.PORT);
			System.out.println("与服务端连接完毕！");
		} catch (Exception e) {
			System.out.println("初始化失败!");
			throw e;
		}
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

			/*
			 * Socket提供了一个获取输出流的方法， 用来将输出写出，而写出的数据就通过 网络发送给服务端了。
			 */
//			OutputStream out = socket.getOutputStream();
//			OutputStreamWriter osw = new OutputStreamWriter(out, "UTF-8");
//			PrintWriter pw = new PrintWriter(osw, true);
//
//			Scanner scanner = new Scanner(System.in);
//
//			while (true) {
//				String message = scanner.nextLine();
//				pw.println(message);
//			}

		} catch (Exception e) {
			System.out.println("客户端运行失败!");
			throw e;
		}
	}

	public static void main(String[] args) {
		try {
			Client client = new Client("127.0.0.1");
			client.start();
		} catch (Exception e) {
			System.out.println("客户端运行失败!");
			e.printStackTrace();
		}
	}
}
