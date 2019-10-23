package com.gousade.pojo;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.Table;


@Table(name="user")
public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	private String userId;
	private String userName;
	private String password;
	private int state;
	private String created;
	private String updated;
	private String salt;
	/** 用户角色 */
	private String userRoles;
	
	/**
	 * 无参构造
	 */
	public User() {
		super();
	}
	
	public User(String id) {
		this.userId=id;
	}
 
	/**
	 * 全参构造
	 */
	public User(String userId,String username, String password, String userRoles,String salt) {
		super();
		this.userId = userId;
		this.userName=username;
		this.password = password;
		this.userRoles = userRoles;
		this.salt = salt;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("userId : " + userId);
		sb.append(",userName : " + userName);
		sb.append(",password : " + password);
		sb.append(",userRoles : " + userRoles);
		return sb.toString();
	}
	
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
	
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getUserRoles() {
		return userRoles;
	}
	public void setUserRoles(String userRoles) {
		this.userRoles = userRoles;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public String getUpdated() {
		return updated;
	}
	public void setUpdated(String updated) {
		this.updated = updated;
	}
}
