package com.gousade.constant;


public enum SystemJobResultStatus {
	NORMAL("1", "正常"),
	ERROR("0", "失败");;

	private String id;
	private String name;

	private SystemJobResultStatus(String value, String name) {
		this.id = value;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}
