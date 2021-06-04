package com.gousade.controller;

import com.gousade.commonutils.ResponseResult;
import com.gousade.utils.BigDecimalCalculator;
import com.gousade.utils.ImageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author woxigousade
 * @date 2021/6/3
 */
@Slf4j
@RestController
@RequestMapping(value = "/admin/smsCode/sliderCaptcha")
public class SliderCaptchaController {

    /**
     * 验证码常量
     */
    private static final String CAPTCHA = "captcha:";

    /**
     * x偏移量允许的误差值
     */
    private static final int X_OFFSET_ERROR_VALUE = 9;

    private static final int INTERVAL_TIME = 500;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @GetMapping("/getSliderCaptcha")
    public ResponseResult getSliderCaptcha() throws IOException {
        String sessionId = request.getRequestedSessionId();
        File backgroundDictionary = new File(ResourceUtils.getURL("classpath:static/sliderImages/background").getPath());
        File[] backgroundFiles = backgroundDictionary.listFiles();
        File backgroundImageFile = Objects.requireNonNull(backgroundFiles)[new Random().nextInt(backgroundFiles.length)];
        File templateDictionary = new File(ResourceUtils.getURL("classpath:static/sliderImages/template").getPath());
        File[] templateFiles = templateDictionary.listFiles();
        File templateImageFile = Objects.requireNonNull(templateFiles)[new Random().nextInt(templateFiles.length)];
        Map<String, Object> resultMap = ImageUtil.getSliderCaptcha(backgroundImageFile, templateImageFile);
        Map<String, Object> cacheMap = new HashMap<>();
        cacheMap.put("randomX", resultMap.get("randomX"));
        cacheMap.put("backgroundImageWidth", resultMap.get("backgroundImageWidth"));
        cacheMap.put("timestamp", Instant.now().toEpochMilli());
        redisTemplate.opsForValue().set(CAPTCHA + sessionId, cacheMap, 5 , TimeUnit.MINUTES);
        resultMap.remove("randomX");
        return ResponseResult.renderSuccess().data(resultMap);
    }

    @PostMapping("/verifySliderCaptcha")
    public ResponseResult verifySliderCaptcha(@RequestBody Map<String, Object> map) throws IOException {
        Object randomX = map.get("randomX");
        if(Objects.isNull(randomX)) {
            return ResponseResult.renderError().message("请进行拼图后再登录。");
        }
        int xOffset = Integer.parseInt(String.valueOf(randomX));
        int width = Integer.parseInt(String.valueOf(map.get("width")));
        String sessionId = request.getRequestedSessionId();
        String key = CAPTCHA + sessionId;
        Object cacheValue = redisTemplate.opsForValue().get(key);
        if(Objects.isNull(cacheValue)) {
            return ResponseResult.renderError().message("验证码已失效，请刷新。");
        }
        Map<String, Object> cacheMap = (Map<String, Object>) cacheValue;
        int xRealOffset = Integer.parseInt(String.valueOf(cacheMap.get("randomX")));
        String backgroundImageWidth = String.valueOf(cacheMap.get("backgroundImageWidth"));
        String timestamp = String.valueOf(cacheMap.get("timestamp"));
        long duration = Instant.now().toEpochMilli() - Long.parseLong(timestamp);
        //得到缩放比例
        double zoomRatio = BigDecimalCalculator.divide(Double.parseDouble(backgroundImageWidth), width, 2);
        if (Math.abs(xRealOffset - (xOffset * zoomRatio)) > X_OFFSET_ERROR_VALUE * zoomRatio || duration < INTERVAL_TIME) {
            return ResponseResult.renderError().message("验证失败，请将滑块拖动到正确位置。");
        }
        redisTemplate.delete(key);
        return ResponseResult.renderSuccess().message("验证成功。");
    }
}
