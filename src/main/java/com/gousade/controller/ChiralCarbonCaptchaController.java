package com.gousade.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.gousade.common.ResponseResult;
import com.gousade.entity.dto.ChiralCarbonCaptchaDTO;
import com.gousade.entity.query.ChiralCarbonCaptchaQuery;
import com.gousade.service.ChiralCarbonCaptchaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author woxigousade <woxigsd@gmail.com>
 * @date 2022/12/03
 */
@Slf4j
@RestController
@RequestMapping(value = "/captcha/chiralCarbon")
public class ChiralCarbonCaptchaController {

	@Autowired
	private ChiralCarbonCaptchaService service;

	@Autowired
	private HttpServletRequest httpServletRequest;

	@SentinelResource(value = "/captcha/chiralCarbon/getChiralCarbonCaptcha", blockHandler = "getChiralCarbonCaptchaBlockHandler",
			fallback = "getChiralCarbonCaptchaFallback")
	@PostMapping("/getChiralCarbonCaptcha")
	public ResponseResult getChiralCarbonCaptcha(@RequestBody ChiralCarbonCaptchaQuery query) {
		ChiralCarbonCaptchaDTO dto = service.getChiralCarbonCaptcha(query);
		return ResponseResult.renderSuccess().data("data", dto);
	}

	public ResponseResult getChiralCarbonCaptchaBlockHandler(ChiralCarbonCaptchaQuery query, BlockException e) {
		log.error("getChiralCarbonCaptchaBlockHandler error", e);
		return ResponseResult.renderError();
	}

	public ResponseResult getChiralCarbonCaptchaFallback(ChiralCarbonCaptchaQuery query, Throwable e) {
		log.error("getChiralCarbonCaptchaFallback error", e);
		return ResponseResult.renderError();
	}


}
