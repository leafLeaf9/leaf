package com.gousade.controller;

import com.gousade.commonutils.ResponseResult;
import com.gousade.service.OssService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

	/**
	 * swagger3不生效，接收到的文件为null
	 *
	 * @param file
	 * @return
	 */
	@RequestMapping(value = "/uploadOssAvatar", method = RequestMethod.POST)
	public ResponseResult uploadOssAvatar(@RequestParam(value = "file") MultipartFile file) {
		String url = ossService.uploadOssAvatar(file);
		return ResponseResult.renderSuccess().message("上传oss文件成功").data("url", url);
	}

}
