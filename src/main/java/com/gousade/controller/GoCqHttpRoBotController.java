package com.gousade.controller;

import com.gousade.entity.dto.CqHttpEvent;
import com.gousade.service.GoCqHttpRoBotService;
import com.gousade.util.JsonUtils;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Api(tags = "go-cqhttp机器人接口")
@Slf4j
@CacheConfig(cacheNames = "redis@goCqHttpRobot")
@RestController
@CrossOrigin
@RequestMapping(value = "/admin/goCqHttpRobot")
public class GoCqHttpRoBotController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private GoCqHttpRoBotService baseService;

    @PostMapping
    public void cqHttpEvent() {
        CqHttpEvent cqHttpEvent = JsonUtils.getCqHttpEvent(request);
        baseService.handleCqHttpEvent(cqHttpEvent);
    }
}
