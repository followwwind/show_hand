package com.wind.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class ServerHandler  implements Runnable{
	
	// 当前线程交互的客户端的Socket
	private Socket socket;
	// 该客户端地址信息
	private String host;

	public ServerHandler(Socket socket) {
		this.socket = socket;
		/*
		 * 通过Socket获取远程计算机地址信息 对于服务端而言，远程计算机就是客户端了
		 */
		InetAddress address = socket.getInetAddress();
		// 获取IP地址
		host = address.getHostAddress();
	}

	
	@Override
	public void run() {
		PrintWriter pw = null;
		try {
			// 广播给所有客户端当前用户上线了
//				sendMessage("[" + host + "]上线了!");

			OutputStream out = socket.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(out, "UTF-8");
			pw = new PrintWriter(osw, true);

			/*
			 * 将该客户端的输出流存入共享集合，以便消息 可以广播给该客户端
			 */
//					addOut(pw);

			/*
			 * 广播给所有客户端，当前在线人数
			 */
//					sendMessage("当前在线[" + allOut.size() + "]人");

			/*
			 * Socket提供了一个方法: InputStream getInputStream() 用于获取远端计算机发送过来的数据
			 */
			InputStream in = socket.getInputStream();

			InputStreamReader isr = new InputStreamReader(in, "UTF-8");

			BufferedReader br = new BufferedReader(isr);
			/*
			 * 服务端读取客户端发送过来的每一句字符串时 由于客户端所在操作系统不同，这里当客户端 断开时的结果也不同。
			 * 当windows的客户端断开连接时，br.readLine 方法会抛出异常
			 * 
			 * 当linux的客户端断开连接时，br.readLine 方法会返回null
			 */
			String message = null;
			while ((message = br.readLine()) != null) {
				// System.out.println(host+"说:"+message);
				// pw.println(host+"说:"+message);
				// 广播给所有客户端
//						sendMessage(host + "说:" + message);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 客户端断开连接了

			// 将该客户端的输出流从共享集合中删除
//					removeOut(pw);

			// 广播给所有客户端当前用户下线了
//					sendMessage("[" + host + "]下线了!");
			/*
			 * 广播给所有客户端，当前在线人数
			 */
//					sendMessage("当前在线[" + allOut.size() + "]人");
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
