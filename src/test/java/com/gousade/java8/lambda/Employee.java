package com.gousade.java8.lambda;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author woxigsd@gmail.com
 * @date 2020-10-26 15:16:10
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

	private int id;
	private String name;
	private int age;
	private double salary;
	private Status status;

	public enum Status {
		FREE, BUSY, VOCATION;
	}

}
