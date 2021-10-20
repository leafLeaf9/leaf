package com.gousade.controller;

import com.gousade.commonutils.ResponseResult;
import com.gousade.mapper.UserMapper;
import com.gousade.pojo.SmsResponseLog;
import com.gousade.pojo.User;
import com.gousade.service.SmsResponseLogService;
import com.gousade.utils.SerializerUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author woxigousade
 * @date 2021/6/10
 */
@Slf4j
@RestController
@RequestMapping(value = "/admin/gousadeTest")
public class GousadeTestController {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private SmsResponseLogService smsResponseLogService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/listSmsLogs")
    public ResponseResult listSmsLogs() {
        List<SmsResponseLog> list = smsResponseLogService.list();
        return ResponseResult.renderSuccess().data("data", list);
    }

    @GetMapping("/testRedisSerializable")
    public ResponseResult testRedisSerializable() {
        Map<String, Object> innerMap = new HashMap<>();
        innerMap.put("string", "stringzzz");
        Map<String, Object> map = new HashMap<>();
        map.put("innerMap", innerMap);
        map.put("string", "sss");
        map.put("user", User.builder().userName("用户名").build());
        redisTemplate.opsForValue().set("testMap", map);
        Map<String, Object> redisMap = (Map<String, Object>) redisTemplate.opsForValue().get("testMap");
        Object object = redisMap.get("user");
        System.out.println(object.getClass());
        System.out.println(object instanceof User);
        List<User> userList = userMapper.selectUserList(map);
        redisTemplate.opsForValue().set("users", userList);
        List<User> users = (List<User>) redisTemplate.opsForValue().get("users");
        List<SmsResponseLog> list = smsResponseLogService.list();
        redisTemplate.opsForValue().set("testList", list);
        List<SmsResponseLog> redisList = (List<SmsResponseLog>) redisTemplate.opsForValue().get("testList");
        map.put("list", list);
        Map<String, Object> outerMap = new HashMap<>();
        outerMap.put("map", map);
        redisTemplate.opsForValue().set("testRedisSerializable", outerMap);
        Map<String, Object> testRedisSerializable = (Map<String, Object>) redisTemplate.opsForValue().get("testRedisSerializable");
        log.info("测试反序列化的结果：{}", testRedisSerializable);
        byte[] listBytes = SerializerUtil.getSerializer().serialize(list);
        List<SmsResponseLog> deserializeList = (List<SmsResponseLog>) SerializerUtil.getSerializer().deserialize(listBytes);
        byte[] bytes = SerializerUtil.getSerializer().serialize(outerMap);
        Map<String, Object> deserializeOuterMap = (Map<String, Object>) SerializerUtil.getSerializer().deserialize(bytes);
        return ResponseResult.renderSuccess();
    }

    @PostMapping(value = "/testTrafficGuidance", produces = {"application/json;charset=UTF-8"}, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseResult testTrafficGuidance() {
        return ResponseResult.renderSuccess();
    }

    @GetMapping("testJsonFormat")
    public ResponseResult testJsonFormat() {
        return ResponseResult.renderSuccess().data("time", LocalDate.now());
    }
}
