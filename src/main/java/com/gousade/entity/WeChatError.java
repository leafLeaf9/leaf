package com.gousade.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

import java.io.Serializable;

/**
 * 微信接口调用失败时请求却还是200成功，需要添加Error类作为微信相关类的基类
 *
 * @author woxigousade
 * @date 2021/9/27
 */
@Data
public class WeChatError implements Serializable {
    @JsonAlias(value = "errcode")
    private Integer errorCode;
    @JsonAlias(value = "errmsg")
    private String errorMessage;
}
