package com.wind.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import com.alibaba.fastjson.JSON;
import com.wind.config.MsgType;

/**
 * 通信通道
 * @author apple
 *
 */
public class SocketChannel {
	
	private Integer id;
	
	private Socket socket;
	
	private BufferedReader input;
	
	private BufferedWriter output;

	public SocketChannel(Socket socket) throws IOException {
		super();
		this.socket = socket;
		this.input = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
		this.output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
		String msg = this.input.readLine();
		MsgModel model = JSON.parseObject(msg, MsgModel.class);
		if(model != null && MsgType.LOGIN.equalType(model.getType())) {
			this.id = Integer.valueOf(model.getData());
			System.out.println(String.format("%d连接服务器成功", this.id));
		}
	}
	
	/**
	 * 关闭socket
	 * @throws IOException
	 */
	public void close() throws IOException {
		if(input != null) {
			input.close();
		}
		if(output != null) {
			output.close();
		}
		if(socket != null) {
			socket.close();
		}
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public BufferedReader getInput() {
		return input;
	}

	public void setInput(BufferedReader input) {
		this.input = input;
	}

	public BufferedWriter getOutput() {
		return output;
	}

	public void setOutput(BufferedWriter output) {
		this.output = output;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
