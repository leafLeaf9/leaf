package com.gousade.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.annotation.JSONField;


@Table(name="user")
public class User implements Serializable{
	private static final long serialVersionUID = -143390288050304564L;
	@Id
	private String userId;
	private String userName;
	private String password;
	private int state;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date created;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date updated;
	private String salt;
	/** 用户角色 */
	private String userRoles;
	private Set<String> roles;
	private Set<String> urls;	
	private String phonenumber;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date lastLoginTime;
	private String delflag;
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
	
	public User(String userId, String userName, String phonenumber, Set<String> urls, Set<String> roles) {
		this.userId = userId;
		this.userName = userName;
		this.phonenumber = phonenumber;
		this.urls = urls;
		this.roles = roles;		
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("userId : " + userId);
		sb.append(",userName : " + userName);		
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
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
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

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getDelflag() {
		return delflag;
	}

	public void setDelflag(String delflag) {
		this.delflag = delflag;
	}
}
