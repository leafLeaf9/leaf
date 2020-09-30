package com.gousade.service.impl;

import com.gousade.service.OssService;
import com.gousade.utils.OssUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @author woxigsd@gmail.com
 * @date 2020-9-23 10:35:47
 * @description OssServiceImpl Oss文件上传业务层
 */
@Service
public class OssServiceImpl implements OssService {

    @Resource
    private OssUtil ossUtil;

    @Override
    public String uploadOssAvatar(MultipartFile file) {
        return ossUtil.uploadOssFile(file);
    }

}
