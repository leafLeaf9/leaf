package com.gousade.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.gousade.annotation.OperationRecord;
import com.gousade.common.ResponseResult;
import com.gousade.mapper.RoleMapper;
import com.gousade.mapper.UserMapper;
import com.gousade.pojo.Role;
import com.gousade.pojo.SmsResponseLog;
import com.gousade.pojo.User;
import com.gousade.service.SmsResponseLogService;
import com.mzt.logapi.starter.annotation.LogRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
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

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private Jackson2JsonRedisSerializer<Object> serializer;

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
        byte[] listBytes = serializer.serialize(list);
        List<SmsResponseLog> deserializeList = (List<SmsResponseLog>) serializer.deserialize(listBytes);
        byte[] bytes = serializer.serialize(outerMap);
        Map<String, Object> deserializeOuterMap = (Map<String, Object>) serializer.deserialize(bytes);
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

    @GetMapping("timeTest")
    public ResponseResult timeTest() {
        return ResponseResult.renderSuccess().data("timeCollection", LocalDate.now());
    }

    @LogRecord(success = "用户{{#user.userName}}的地址是{{#p0}}", type = "logTest", bizNo = "logTest001")
    @OperationRecord(operationNum = 9, operationDescription = "'用户{{' + #user.userName + '}}的地址是{{' + {#p0} + '}}'")
//    @OperationRecord(operationNum = 9, operationDescription = "'旧地址是' +{#p0}+ ' '+#p0+ ' '+'#p0'+' '+#oldAddress + '用户名称是' + ' ' + #user.userName")
    @PostMapping("logTest")
    public ResponseResult logTest(@RequestParam(value = "oldAddress") String oldAddress, @RequestBody User user) {
        return ResponseResult.renderSuccess().data("timeCollection", LocalDate.now());
    }

    @PostMapping("postTimeTest")
    public ResponseResult postTimeTest() {
        return ResponseResult.renderSuccess().data("timeCollection", LocalDate.now());
    }

    @GetMapping("timeSelectOne")
    public ResponseResult timeSelectOne() {
        LambdaQueryWrapper<Role> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(Role::getName, "测试").orderByAsc(Role::getCreateTime);
        List<String> ids = new ArrayList<>();
        wrapper.in(Role::getName, ids);
        Role role = roleMapper.selectOne(wrapper);
        return ResponseResult.renderSuccess().data("selectOne", role);
    }
}
