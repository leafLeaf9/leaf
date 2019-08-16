package cn.edu.zstu.manage.pojo;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="proj")
public class Project {
	@Id
	private String projId;
	private String projName;
	private String userId;
	private int level;
	private String phone;
	private String major;
	public String getProjId() {
		return projId;
	}
	public void setProjId(String projId) {
		this.projId = projId;
	}
	public String getProjName() {
		return projName;
	}
	public void setProjName(String projName) {
		this.projName = projName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	@Override
	public String toString() {
		return "Project [projId=" + projId + ", projName=" + projName + ", userId=" + userId + ", level=" + level
				+ ", phone=" + phone + ", major=" + major + "]";
	}
}
