package com.gousade.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author chuzizhuo
 * @date 2022/1/12
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class SubUserImpl extends User {
    private String sub1;
    private String sub2;
}
