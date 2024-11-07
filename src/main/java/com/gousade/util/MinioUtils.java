package com.gousade.util;


import com.gousade.common.ResponseResult;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.http.Method;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.InputStream;

@Component
public class MinioUtils {

    @Value("${minio.bucketName}")
    private String bucketName;

    @Resource
    private MinioClient minioClient;

    public ResponseResult uploadFile(MultipartFile file) {
        String fileName = System.currentTimeMillis() + file.getOriginalFilename();
        try (InputStream fi = file.getInputStream()) {
            PutObjectArgs putObjectArgs = PutObjectArgs.builder().bucket(bucketName).contentType(file.getContentType()).object(fileName).stream(fi, fi.available(), -1).build();
            minioClient.putObject(putObjectArgs);
        } catch (Exception e) {
            throw new RuntimeException("文件上传失败", e);
        }
        return ResponseResult.renderSuccess().message(fileName);
    }

    public ResponseResult getPreviewUrl(String objectName) {
        try {
            GetPresignedObjectUrlArgs urlArgs = GetPresignedObjectUrlArgs.builder().bucket(bucketName).object(objectName).method(Method.GET).build();
            return ResponseResult.renderSuccess().data("data", minioClient.getPresignedObjectUrl(urlArgs));
        } catch (Exception e) {
            throw new RuntimeException("获取预览链接失败", e);
        }
    }
}
