package com.gousade.model;

import java.io.Serializable;

/**
 * 用户模型
 * 注意:此模型必须能够序列化，即:必须implements Serializable
 *
 * @author JustryDeng
 * @Date 2018年8月23日 下午4:35:44
 */
public class User implements Serializable{
 
	/** 序列化UID为-4914585368925337032L */
	private static final long serialVersionUID = -4914585368925337032L;
 
	/** 用户名 */
	private String userId;
	
	private String userName;
 
	/** 用户密码 */
	private String password;
 
	/** 用户角色 */
	private String userRoles;
	
	private String salt;
 
	/**
	 * 无参构造
	 */
	public User() {
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
 

 
	public String getPassword() {
		return password;
	}
 
	public void setPassword(String password) {
		this.password = password;
	}
 
	public String getUserRoles() {
		return userRoles;
	}
 
	public void setUserRoles(String userRoles) {
		this.userRoles = userRoles;
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

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}
 
}