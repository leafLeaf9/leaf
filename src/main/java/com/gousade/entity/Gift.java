package com.gousade.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author woxigsd@gmail.com
 * @date 2020-10-14 14:34:48
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Gift implements Serializable {

    private static final long serialVersionUID = 7306619128783938198L;

    private int id;            //奖品Id
    private String name;    //奖品名称
    private Double prob;    //获奖概率

}
