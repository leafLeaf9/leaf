package com.gousade.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "miHoYo接口")
@Slf4j
@CacheConfig(cacheNames = "redis@miHoYo")
@RestController
@CrossOrigin
@RequestMapping(value = "/admin/miHoYo")
public class MiHoYoController {
}
