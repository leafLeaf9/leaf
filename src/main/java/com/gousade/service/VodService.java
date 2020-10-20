package com.gousade.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author woxigsd@gmail.com
 * @date 2020-10-20 9:50:48
 * @description 
 */
public interface VodService {

	String uploadAliyunVideo(MultipartFile file);

	String getVideoPlayAuth(String videoId);

}
