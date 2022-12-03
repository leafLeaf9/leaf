package com.gousade.entity.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author woxigousade <woxigsd@gmail.com>
 * @date 2022/12/03
 */
@Data
@Builder
public class ChiralCarbonCaptchaDTO implements Serializable {
	private String base64;
	private List<String> areas;
}
