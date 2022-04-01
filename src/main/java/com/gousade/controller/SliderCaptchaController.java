package com.gousade.controller;

import cn.hutool.core.util.IdUtil;
import com.gousade.common.ResponseResult;
import com.gousade.pojo.SliderCaptchaDto;
import com.gousade.util.BigDecimalCalculator;
import com.gousade.util.ImageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Instant;
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
     * 验证码过期时间，5分钟
     */
    private static final int CAPTCHA_EXPIRE_TIME = 5;

    /**
     * x偏移量允许的误差值
     */
    private static final int X_OFFSET_ERROR_VALUE = 9;

    private static final int INTERVAL_TIME = 500;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @GetMapping("/test")
    public ResponseResult test() throws IOException {
        File templateDictionary = new File(ResourceUtils.getURL("classpath:static/sliderImages/template").getPath());
        File[] templateFiles = templateDictionary.listFiles();
        for (File templateFile : Objects.requireNonNull(templateFiles)) {
            BufferedImage templateImage = ImageIO.read(templateFile);
            int templateWidth = templateImage.getWidth();
            int templateHeight = templateImage.getHeight();
            int rgb0 = templateImage.getRGB(0, 0);
            int rgb = templateImage.getRGB(templateWidth / 2, templateHeight / 2);
            log.info(templateFile.getName() + "左上角rgb为" + rgb0 + ",中心点的RGB值为" + rgb);
        }
        return ResponseResult.renderSuccess();
    }

    @GetMapping("/getSliderCaptcha")
    public ResponseResult getSliderCaptcha() throws IOException {
        File backgroundDictionary = new File(ResourceUtils.getURL("classpath:static/sliderImages/background").getPath());
        File[] backgroundFiles = backgroundDictionary.listFiles();
        File backgroundImageFile = Objects.requireNonNull(backgroundFiles)[new Random().nextInt(backgroundFiles.length)];
        File templateDictionary = new File(ResourceUtils.getURL("classpath:static/sliderImages/template").getPath());
        File[] templateFiles = templateDictionary.listFiles();
        File templateImageFile = Objects.requireNonNull(templateFiles)[new Random().nextInt(templateFiles.length)];
        SliderCaptchaDto sliderCaptchaDto = ImageUtil.getSliderCaptcha(backgroundImageFile, templateImageFile);
        SliderCaptchaDto cacheSliderCaptchaDto = SliderCaptchaDto.builder()
                .id(IdUtil.simpleUUID())
                .randomX(sliderCaptchaDto.getRandomX())
                .backgroundImageWidth(sliderCaptchaDto.getBackgroundImageWidth())
                .timestamp(Instant.now().toEpochMilli()).build();
        redisTemplate.opsForValue().set(
                CAPTCHA + cacheSliderCaptchaDto.getId(), cacheSliderCaptchaDto, CAPTCHA_EXPIRE_TIME, TimeUnit.MINUTES);
        sliderCaptchaDto.setId(cacheSliderCaptchaDto.getId());
        return ResponseResult.renderSuccess().data("data", sliderCaptchaDto);
    }

    @PostMapping("/verifySliderCaptcha")
    public ResponseResult verifySliderCaptcha(@RequestBody SliderCaptchaDto sliderCaptchaDto) {
        if (sliderCaptchaDto.getRandomX() <= 0 || sliderCaptchaDto.getId() == null) {
            return ResponseResult.renderError().message("请进行拼图后再登录。");
        }
        int xOffset = sliderCaptchaDto.getRandomX();
        int width = sliderCaptchaDto.getBackgroundImageWidth();
        SliderCaptchaDto cacheValue = (SliderCaptchaDto) redisTemplate.opsForValue().get(CAPTCHA + sliderCaptchaDto.getId());
        if (Objects.isNull(cacheValue)) {
            return ResponseResult.renderError().message("验证码已失效，请刷新。");
        }
        int xRealOffset = cacheValue.getRandomX();
        int backgroundImageWidth = cacheValue.getBackgroundImageWidth();
        long timestamp = cacheValue.getTimestamp();
        long duration = Instant.now().toEpochMilli() - timestamp;
        //得到缩放比例
        double zoomRatio = BigDecimalCalculator.divide(backgroundImageWidth, width, 2);
        if (Math.abs(xRealOffset - (xOffset * zoomRatio)) > X_OFFSET_ERROR_VALUE * zoomRatio || duration < INTERVAL_TIME
                || !cacheValue.getId().equals(sliderCaptchaDto.getId())) {
            return ResponseResult.renderError().message("验证失败，请将滑块拖动到正确位置。");
        }
        redisTemplate.delete(sliderCaptchaDto.getId());
        return ResponseResult.renderSuccess().message("验证成功。");
    }
}
