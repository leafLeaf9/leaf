package com.gousade.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenshinAward implements Serializable {

    private String icon;

    private String name;

    private Integer cnt;

}
