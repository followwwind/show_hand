package com.wind.model;

import java.io.Serializable;

/**
 * 消息模型
 * @author apple
 *
 */
public class MsgModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer type;
	
	private String data;

	

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
	
}
