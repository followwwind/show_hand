package com.wind.model;

import java.math.BigDecimal;

/**
 * 用户信息
 * @author follow
 */
public class User {
    
    private Integer id;
    
    /**
     * 账号
     */
    private String username;
    
    /**
     * 昵称
     */
    private String nickname;
    
    /**
     * 密码
     */
    private String password;
    
    /**
     * 余额
     */
    private BigDecimal amount;
    

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	} 
}
