package com.gousade.pojo;

import javax.persistence.Id;
import javax.persistence.Table;


@Table(name="user")
public class User {
	@Id
	private String userId;
	private String userName;
	private String password;
	private int state;
	private String created;
	private String updated;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getcreated() {
		return created;
	}
	public void setcreated(String created) {
		this.created = created;
	}
	public String getupdated() {
		return updated;
	}
	public void setupdated(String updated) {
		this.updated = updated;
	}
}
