package com.gousade.entity;

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
    private String headimgurl;
    private String[] privilege;
    private String unionid;
}
