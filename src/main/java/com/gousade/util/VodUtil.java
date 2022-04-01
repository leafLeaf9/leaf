package com.gousade.util;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoRequest;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse.PlayInfo;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author woxigsd@gmail.com
 * @date 2020-10-20 9:58:41
 * @description uploadAliyunVideoUtility
 */
@Slf4j
@Component
public class VodUtil {

    @Value("${aliyun.accessKeyId}")
    private String accessKeyId;

    @Value("${aliyun.accessKeySecret}")
    private String accessKeySecret;

    public static DefaultAcsClient initVodClient(String accessKeyId, String accessKeySecret) throws ClientException {
        String regionId = "cn-shanghai"; // 点播服务接入区域
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
        DefaultAcsClient client = new DefaultAcsClient(profile);
        return client;
    }

    public String uploadAliyunVideo(MultipartFile file) {
        try {
            String fileName = file.getOriginalFilename();
            String title = fileName.substring(0, fileName.lastIndexOf("."));
            InputStream inputStream = file.getInputStream();
            UploadStreamRequest request = new UploadStreamRequest(accessKeyId, accessKeySecret, title, fileName,
                    inputStream);
            UploadVideoImpl uploader = new UploadVideoImpl();
            UploadStreamResponse response = uploader.uploadStream(request);
            String videoId = null;
            if (response.isSuccess()) {
                videoId = response.getVideoId();
            } else { // 如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。
                videoId = response.getVideoId();
                log.info("VideoId={}", response.getVideoId());
                log.info("ErrorCode={}", response.getCode());
                log.info("ErrorMessage={}", response.getMessage());
            }
            return videoId;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getVideoPlayAuth(String videoId) {
        try {
            DefaultAcsClient client = initVodClient(accessKeyId, accessKeySecret);
            GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
            request.setVideoId(videoId);
            GetVideoPlayAuthResponse response = client.getAcsResponse(request);
            String playAuth = response.getPlayAuth();
            return playAuth;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void getPlayInfo(String videoId) {
        try {
            DefaultAcsClient client = initVodClient(accessKeyId, accessKeySecret);
            GetPlayInfoRequest request = new GetPlayInfoRequest();
            request.setVideoId(videoId);
            GetPlayInfoResponse response = client.getAcsResponse(request);
            List<PlayInfo> playInfoList = response.getPlayInfoList();
            // 播放地址
            playInfoList.forEach(playInfo -> {
                log.info("PlayInfo.PlayURL = {}", playInfo.getPlayURL());
            });
            // Base信息
            log.info("VideoBase.Title = {}", response.getVideoBase().getTitle());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
