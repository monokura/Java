package com.example.webchat.db.bean;

import java.util.Date;

public class MessageBean {
	// 発言日時
	private Date date = null;

	// 発言者名
	private String name = null;

	// 発言内容
	private String message = null;

	MessageBean(Date date, String name, String message){
		this.date = date;
		this.name = name;
		this.message = message;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
