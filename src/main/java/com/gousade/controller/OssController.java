package com.gousade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gousade.commonutils.ResponseResult;
import com.gousade.service.OssService;

import io.swagger.annotations.Api;

/**
 * @author woxigsd@gmail.com
 * @date 2020-9-23 10:29:20
 * @description OssUserController Oss文件上传控制层
 */
@Api(tags = "oss")
@RestController
@CrossOrigin
@RequestMapping(value = "/admin/oss")
public class OssController {
	
	@Autowired
	private OssService ossService;
	
//	@RequestMapping(value = "/uploadOssFile", method = RequestMethod.POST)
	@PostMapping
	public ResponseResult uploadOssFile(/*@RequestParam(value = "file")*/ MultipartFile file) {
		String url = ossService.uploadAvatarFile(file);
        return ResponseResult.renderSuccess().data("url",url);
	}

}
