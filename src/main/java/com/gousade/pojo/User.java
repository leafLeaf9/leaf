package com.gousade.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(/*value = "User",*/ description = "用户实体类")
@Data
public class User implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1283526925605483104L;

	private String id;
	
	private String userId;
	
	private String userName;
	
	@JSONField(serialize = false)
	private String password;
	
	@JSONField(serialize = false)
	private String salt;

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;

    private String remark;

    private String phoneNumber;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date lastlogintime;

    private Integer delflag;
    
    @ApiModelProperty(value = "用户头像路径")
    private String avatarPath;
    
    private String userRoles;
    
	private Set<String> roles;
	
	private Set<String> urls;

	private String roleIds;
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
		this.phoneNumber = phonenumber;
		this.urls = urls;
		this.roles = roles;		
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
	
}
