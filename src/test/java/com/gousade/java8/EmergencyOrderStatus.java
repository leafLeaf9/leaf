package com.gousade.java8;

import lombok.Getter;

import java.util.Arrays;

/**
 * @author Administrator
 * @date 2021/5/3 14:30
 */
@Getter
public enum EmergencyOrderStatus {

	/**
	 * 待执行
	 */
	TODO("1", "任务已分配"),

	/**
	 * 执行中
	 */
	DOING("2", "前往现场中"),

	/**
	 * 已完成
	 */
	DONE("3", "已完成任务"),

	/**
	 * 已取消
	 */
	CANCELLED("4", "已取消任务"),

	/**
	 * 已拒绝
	 */
	REFUSED("5", "已拒绝任务"),

	/**
	 * 已到达
	 */
	ARRIVED("6", "已到达现场"),

	/**
	 * 已延期
	 */
	DELAYED("7", "已延期");

	private final String status;

	private final String description;

	EmergencyOrderStatus(String status, String description) {
		this.status = status;
		this.description = description;
	}

	public static String getDescription(String status) {
		return Arrays.stream(
				EmergencyOrderStatus.values()).filter(emergencyOrderStatus -> emergencyOrderStatus.getStatus().equals(status))
				.findFirst()
				.map(emergencyOrderStatus -> emergencyOrderStatus.description)
				.orElse(null);
	}

}

