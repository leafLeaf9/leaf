package com.gousade.entity.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenshinSign implements Serializable {
    /**
     * restTemplate序列化使用的是Jackson
     */
    @JSONField(name = "act_id")
    @JsonProperty(value = "act_id")
    private String actId;
    private String region;
    private String uid;
}
