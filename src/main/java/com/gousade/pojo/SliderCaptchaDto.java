package com.gousade.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author woxigousade
 * @date 2021/6/4
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SliderCaptchaDto implements Serializable, TestInterface {

    private String id;

    private String backgroundImage;

    private String sliderImage;

    private String userId;

    private String password;

    @JSONField(serialize = false)
    private int randomX;

    private int randomY;

    private int backgroundImageWidth;

    @JSONField(serialize = false)
    private long timestamp;
}
