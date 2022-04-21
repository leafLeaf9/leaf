package com.gousade.controller;

import com.gousade.entity.dto.CqHttpEvent;
import com.gousade.entity.dto.CqHttpResponse;
import com.gousade.entity.dto.CqTencentQQMember;
import com.gousade.redis.RedisUtil;
import com.gousade.util.JsonUtils;
import com.gousade.util.RemoteObjectUtil;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Api(tags = "用户管理")
@Slf4j
@CacheConfig(cacheNames = "redis@goCqHttpRobot")
@RestController
@CrossOrigin
@RequestMapping(value = "/admin/goCqHttpRobot")
public class RoBotController {
    private static final String robotRequestUrl = "http://127.0.0.1:5700/";

    @Autowired
    private HttpServletRequest request;

    @Resource
    private RedisUtil redisUtil;

    @PostMapping
    public void cqHttpEvent() {
//        JSONObject jsonObject = JsonUtils.getJSONObject(request);
        CqHttpEvent cqHttpEvent = JsonUtils.getCqHttpEvent(request);
//        CqHttpEvent cqHttpEvent = jsonObject.toJavaObject(CqHttpEvent.class);
        if ("message".equals(cqHttpEvent.getPostType()) && "group".equals(cqHttpEvent.getMessageType())) {
            handleGroupMessage(cqHttpEvent);
        }
    }

    private void handleGroupMessage(CqHttpEvent cqHttpEvent) {
        String message = cqHttpEvent.getMessage();
        if (message.contains("禁言")) {
            handleGroupBanSomebody(cqHttpEvent);
        }
    }

    private void handleGroupBanSomebody(CqHttpEvent cqHttpEvent) {
        String message = cqHttpEvent.getMessage();
        if (!isRobotAdmin(cqHttpEvent.getUserId(), cqHttpEvent.getGroupId())) {
            String refusedMessage = String.format("[CQ:at,qq=%s]您没有管理员权限，无法执行禁言操作。",
                    cqHttpEvent.getUserId());
            sendGroupMsg(cqHttpEvent.getGroupId(), refusedMessage);
            return;
        }
        String banCQGrammar = "CQ:at,qq=";
        String bannedId = message.substring(message.lastIndexOf(banCQGrammar) + banCQGrammar.length(),
                message.lastIndexOf("]"));
        long duration = Long.parseLong(message.substring(message.lastIndexOf(" ") + 1));
        String url = robotRequestUrl + "set_group_ban?group_id={group_id}&user_id={user_id}&duration={duration}";
        Map<String, Object> map = new HashMap<>();
        map.put("group_id", cqHttpEvent.getGroupId());
        map.put("user_id", bannedId);
        map.put("duration", duration);
        ResponseEntity<String> result = RemoteObjectUtil.getSimpleRestTemplate().getForEntity(url, String.class, map);
        if (HttpStatus.OK.equals(result.getStatusCode())) {
            String sentMessage = String.format("执行禁言成功。[CQ:at,qq=%s]被禁言%d秒。", bannedId, duration);
            sendGroupMsg(cqHttpEvent.getGroupId(), sentMessage);
        }
    }

    private void sendGroupMsg(String groupId, String message) {
        String url = robotRequestUrl + "send_group_msg?group_id={group_id}&message={message}";
        Map<String, Object> map = new HashMap<>();
        map.put("group_id", groupId);
        map.put("message", message);
        ResponseEntity<String> result = RemoteObjectUtil.getSimpleRestTemplate().getForEntity(url, String.class, map);
        if (HttpStatus.OK.equals(result.getStatusCode())) {
            log.info("发送群消息成功, 群号: {}, 消息体: {}", groupId, message);
        }
    }

    private boolean isRobotAdmin(String userId, String groupId) {
        CqTencentQQMember groupMemberInfo = getGroupMemberInfo(groupId, userId);
        String adminUserIds = String.valueOf(redisUtil.get("goCqHttpRobot:robotAdmin"));
        return !ObjectUtils.isEmpty(userId)
                && ((!ObjectUtils.isEmpty(adminUserIds) && adminUserIds.contains(userId))
                || isGroupMemberAdmin(groupMemberInfo));
    }

    /**
     * 获取群成员信息
     *
     * @param groupId 群号
     * @param userId  用户id
     * @return {@link CqTencentQQMember}
     */
    private CqTencentQQMember getGroupMemberInfo(String groupId, String userId) {
        String url = robotRequestUrl + "get_group_member_info?group_id={group_id}&user_id={user_id}";
        Map<String, Object> map = new HashMap<>();
        map.put("group_id", groupId);
        map.put("user_id", userId);
        ResponseEntity<CqHttpResponse> result = RemoteObjectUtil.getSimpleRestTemplate().getForEntity(url,
                CqHttpResponse.class, map);
        return Objects.requireNonNull(result.getBody()).getData().toJavaObject(CqTencentQQMember.class);
    }

   /* public static void main(String[] args) {
        CqTencentQQMember memberInfo = new RoBotController().getGroupMemberInfo("93551088", "1207366201");
        System.out.println(memberInfo);
    }*/

    private boolean isGroupMemberAdmin(CqTencentQQMember member) {
        if (member == null) {
            return false;
        }
        return Objects.equals(member.getRole(), "owner") || Objects.equals(member.getRole(), "admin");
    }

}
