package com.gousade.java8.lambda;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author woxigousade
 * @date 2021/7/14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeWrapper {
    private String Status;
    private List<Employee> employees;
}
