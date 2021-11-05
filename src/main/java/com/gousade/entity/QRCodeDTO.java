package com.gousade.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author woxigousade
 * @date 2021/9/26
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class QRCodeDTO implements Serializable {
    private String appId;
    private String redirectUri;
    private String scope;
    private String state;
}
