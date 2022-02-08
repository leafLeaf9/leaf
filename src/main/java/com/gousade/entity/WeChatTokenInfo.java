package com.gousade.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author woxigousade
 * @date 2021/9/26
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WeChatTokenInfo extends WeChatError {
    @JsonAlias(value = "access_token")
    private String accessToken;
    @JsonAlias(value = "expires_in")
    private Integer expiresIn;
    @JsonAlias(value = "refresh_token")
    private String refreshToken;
    private String openid;
    private String scope;
    @JsonAlias(value = "unionid")
    private String unionId;
}
