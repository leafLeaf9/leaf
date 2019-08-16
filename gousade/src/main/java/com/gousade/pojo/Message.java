package com.gousade.pojo;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="message")
public class Message {
	@Id
	private String messId;
	private String userId;
	private String message;
	private int state;
	private String time;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getMessId() {
		return messId;
	}
	public void setMessId(String messId) {
		this.messId = messId;
	}
}
