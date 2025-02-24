package com.gousade.controller;

import com.gousade.common.ResponseResult;
import com.gousade.util.MinioUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@RestController
@RequestMapping("/minio")
public class MinIOFileController {
    @Resource
    private MinioUtils minioUtils;

    @PostMapping("/upload")
    public ResponseResult upload(MultipartFile file) {
        return minioUtils.uploadFile(file);
    }

    @GetMapping("/getPreviewUrl")
    public ResponseResult getPreviewUrl(String fileName) {
        return minioUtils.getPreviewUrl(fileName);
    }
}
