package com.gousade.controller;

import com.gousade.commonutils.ResponseResult;
import com.gousade.entity.SecretJasypt;
import com.gousade.pojo.OperationRecordLog;
import com.gousade.pojo.SmsResponseLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author woxigsd@gmail.com
 * @date 2020-8-31 14:38:52
 * @description 在controller必须增加返回值为实体类的接口，否则该实体类不会在swagger文档中显示
 */
@Api(tags = "swagger接口")
@Slf4j
@RestController
@RequestMapping(value = "/swagger")
public class Swagger3Controller {

	@ApiOperation("仅用于在swagger文档中显示SmsResponse实体类信息")
	@RequestMapping(value = "/getSmsResponse", method = RequestMethod.POST)
	public SmsResponseLog getSmsResponse() {
		log.info("getSmsResponse");
		return null;
	}

	@RequestMapping(value = "/getOperationRecordLog", method = RequestMethod.POST)
	public OperationRecordLog getOperationRecordLog() {
		return null;
	}

	@RequestMapping(value = "/getSecretJasypt", method = RequestMethod.POST)
	public SecretJasypt getSecretJasypt() {
		return null;
	}

	@RequestMapping(value = "/getResponseResult", method = RequestMethod.POST)
	public ResponseResult getResponseResult() {
		return null;
	}

}
