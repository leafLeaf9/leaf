package com.gousade.service.impl;

import com.gousade.service.VodService;
import com.gousade.utils.VodUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @author woxigsd@gmail.com
 * @date 2020-10-20 9:51:27
 * @description
 */
@Service
public class VodServiceImpl implements VodService {

    @Resource
    private VodUtil vodUtil;

    @Override
    public String uploadAliyunVideo(MultipartFile file) {
        return vodUtil.uploadAliyunVideo(file);
    }

    @Override
    public String getVideoPlayAuth(String videoId) {
        return vodUtil.getVideoPlayAuth(videoId);
    }

}
