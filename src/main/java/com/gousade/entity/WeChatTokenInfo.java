package com.gousade.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author woxigousade
 * @date 2021/9/26
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WeChatTokenInfo extends WeChatError {
    private String access_token;
    private Integer expires_in;
    private String refresh_token;
    private String openid;
    private String scope;
    private String unionid;
}
