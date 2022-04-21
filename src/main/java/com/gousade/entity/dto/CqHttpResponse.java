package com.gousade.entity.dto;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.io.Serializable;

@Data
public class CqHttpResponse implements Serializable {
    private int retcode;
    private String status;
    private JSONObject data;
}
