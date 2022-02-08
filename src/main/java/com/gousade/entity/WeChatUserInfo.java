package com.gousade.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author woxigousade
 * @date 2021/9/27
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WeChatUserInfo extends WeChatError {
    private String openid;
    private String nickname;
    private Integer sex;
    private String province;
    private String city;
    private String country;
    @JsonAlias(value = "headimgurl")
    private String headImgUrl;
    private String[] privilege;
    @JsonAlias(value = "unionid")
    private String unionId;
}
