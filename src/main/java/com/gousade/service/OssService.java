package com.gousade.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author woxigsd@gmail.com
 * @date 2020-9-23 10:34:18
 * @description OssService
 */
public interface OssService {

	String uploadOssAvatar(MultipartFile file);

}
