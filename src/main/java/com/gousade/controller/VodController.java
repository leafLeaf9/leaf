package com.gousade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gousade.commonutils.ResponseResult;
import com.gousade.service.VodService;

import io.swagger.annotations.Api;

/**
 * @author woxigsd@gmail.com
 * @date 2020-10-20 9:46:04
 * @description 
 */
@Api(tags = "vod")
@RestController
@CrossOrigin
@RequestMapping(value = "/admin/vod")
public class VodController {
	
	@Autowired
	private VodService vodService;
	
	@RequestMapping(value = "/uploadAliyunVideo", method = RequestMethod.POST)
	public ResponseResult uploadAliyunVideo(MultipartFile file) {
		String videoId = vodService.uploadAliyunVideo(file);
		return ResponseResult.renderSuccess().message("阿里云视频上传成功").data("videoId", videoId);
	}
	
	@RequestMapping(value = "/getVideoPlayAuth", method = RequestMethod.POST)
	public ResponseResult getVideoPlayAuth(String videoId) {
		String playAuth = vodService.getVideoPlayAuth(videoId);
		return ResponseResult.renderSuccess().message("阿里云视频上传成功").data("playAuth", playAuth);
	}

}
