package com.wind.config;

/**
 * 消息类型
 * @author apple
 *
 */
public enum MsgType {
	
	LOGIN(1),
	
	JOIN(2),
	
	SEND(3),
	
	;
	private final Integer type;

	MsgType(Integer type) {
		this.type = type;
	}
	
	public boolean equalType(Integer type) {
		return this.type.equals(type);
	}

	public Integer getType() {
		return type;
	}
}
