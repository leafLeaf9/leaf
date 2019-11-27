package com.gousade.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Id;
import javax.persistence.Table;


@Table(name="user")
public class User implements Serializable{
	private static final long serialVersionUID = -143390288050304564L;
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
	private Set<String> roles;
	private Set<String> urls;	
	private String mobile;
	private String lastLoginTime;
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
	
	public User(String userId, String userName, String mobile, Set<String> urls, Set<String> roles) {
		this.userId = userId;
		this.userName = userName;
		this.mobile = mobile;
		this.urls = urls;
		this.roles = roles;		
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

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

	public Set<String> getUrls() {
		return urls;
	}

	public void setUrls(Set<String> urls) {
		this.urls = urls;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
}
