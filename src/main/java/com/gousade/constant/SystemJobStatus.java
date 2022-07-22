package com.gousade.constant;


public enum SystemJobStatus {
	NORMAL("0", "正常"),
	PAUSE("1", "暂停");

	private String id;
	private String name;

	SystemJobStatus(String value, String name) {
		this.id = value;
		this.name = name;
	}

	public static SystemJobStatus get(String id) {
		for (SystemJobStatus sstemJobStatus : values()) {
			if (sstemJobStatus.getId().equals(id)) {
				return sstemJobStatus;
			}
		}
		return null;
	}

	public static String getName(String id) {
		SystemJobStatus status = get(id);
		if (status == null) {
			return null;
		}
		return status.name;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}
