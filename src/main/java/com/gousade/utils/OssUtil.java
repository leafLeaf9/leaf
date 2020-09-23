package com.gousade.utils;

import java.io.IOException;
import java.io.InputStream;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;

/**
 * @author woxigsd@gmail.com
 * @date 2020-9-23 14:27:29
 * @description aliyunOss
 */
@Component
public class OssUtil {
	
	@Value("${aliyun.accessKeyId}")
	private String accessKeyId;
    
	@Value("${aliyun.accessKeySecret}")
    private String accessKeySecret;
    
	@Value("${aliyun.oss.bucketName}")
    private String bucketName;
    
	@Value("${aliyun.oss.endpoint}")
    private String endpoint;
	
	public String uploadAvatarFile(MultipartFile file) {
		try {
		// 创建OSSClient实例。
		OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
		// 上传文件流。
		InputStream inputStream = file.getInputStream();
		String fileName = file.getOriginalFilename();
		String uuid = UUID.randomUUID().toString().replace("-", "");
		String datePath = ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		fileName = datePath+"/"+uuid+fileName;
		ossClient.putObject(bucketName, fileName, inputStream);
		// 关闭OSSClient。
		ossClient.shutdown();
		String url = "https://"+bucketName+"."+endpoint+"/"+fileName;
		return url;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}
