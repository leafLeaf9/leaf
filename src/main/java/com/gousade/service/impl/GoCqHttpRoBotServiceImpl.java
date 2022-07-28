package com.gousade.service.impl;

import com.gousade.entity.dto.CqHttpEvent;
import com.gousade.entity.dto.CqHttpResponse;
import com.gousade.entity.dto.CqTencentQQMember;
import com.gousade.redis.RedisUtils;
import com.gousade.service.GoCqHttpRoBotService;
import com.gousade.service.MiHoYoService;
import com.gousade.util.RemoteObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
@CacheConfig(cacheNames = "redis@goCqHttpRobot")
@Service
public class GoCqHttpRoBotServiceImpl implements GoCqHttpRoBotService {
    private static final String robotRequestUrl = "http://127.0.0.1:5700/";

    @Resource
    private RedisUtils redisUtils;

    @Autowired
    private MiHoYoService miHoYoService;

    @Override
    public void handleCqHttpEvent(CqHttpEvent event) {
        if ("message".equals(event.getPostType()) /*&& "group".equals(event.getMessageType())*/) {
            handleMessage(event);
        }
    }

    private void handleMessage(CqHttpEvent event) {
        String message = event.getMessage();
        if (message.contains("禁言")) {
            handleGroupBanSomebody(event);
        }
        if (message.startsWith("绑定米游社cookie")) {
            handleBindMiHoYoCookie(event);
        }
        if (message.equals("签到")) {
            handleMiHoYoSign(event);
        }
        if (message.equals("cookie教程") || message.equals("ck教程")) {
            handleSendCourse(event);
        }
    }

    private void handleSendCourse(CqHttpEvent event) {
        String courseUrl = "https://shimo.im/docs/dlrDMxoybAcmrwKU";
        String sentMessage = String.format("cookie教程地址: %s\n绑定米游社cookie方式为: "
                + "在群内发送以下消息\n绑定米游社cookie 你的cookie", courseUrl);
        sendGroupMsg(event.getGroupId(), sentMessage);
    }

    private void handleBindMiHoYoCookie(CqHttpEvent event) {
        miHoYoService.bindMiHoYoCookie(event);
        String sentMessage = String.format("[CQ:at,qq=%s]绑定米游社cookie成功。", event.getUserId());
        sendGroupMsg(event.getGroupId(), sentMessage);
    }

    private void handleMiHoYoSign(CqHttpEvent event) {
        miHoYoSign(event.getUserId(), event.getGroupId());
    }

    @Override
    public void miHoYoSign(String userId, String groupId) {
        Object cookie = redisUtils.get(MiHoYoServiceImpl.MI_HO_YO_COOKIE_KEY_PREFIX + userId);
        if (cookie == null) {
            String sentMessage = String.format("未查询到对应的cookie，请先绑定米游社cookie。[CQ:at,qq=%s]", userId);
            sendGroupMsg(groupId, sentMessage);
            return;
        }
        String resultMessage = miHoYoService.doSign(String.valueOf(cookie));
        String sentMessage = String.format("[CQ:at,qq=%s]\n", userId);
        sendGroupMsg(groupId, sentMessage + resultMessage);
    }

    private void handleGroupBanSomebody(CqHttpEvent event) {
        String message = event.getMessage();
        if (!isRobotAdmin(event.getUserId(), event.getGroupId())) {
            String refusedMessage = String.format("[CQ:at,qq=%s]您没有管理员权限，无法执行禁言操作。",
                    event.getUserId());
            sendGroupMsg(event.getGroupId(), refusedMessage);
            return;
        }
        String banCQGrammar = "CQ:at,qq=";
        String bannedId = message.substring(message.lastIndexOf(banCQGrammar) + banCQGrammar.length(),
                message.lastIndexOf("]"));
        long duration = Long.parseLong(message.substring(message.lastIndexOf(" ") + 1));
        String url = robotRequestUrl + "set_group_ban?group_id={group_id}&user_id={user_id}&duration={duration}";
        Map<String, Object> map = new HashMap<>();
        map.put("group_id", event.getGroupId());
        map.put("user_id", bannedId);
        map.put("duration", duration);
        ResponseEntity<String> result = RemoteObjectUtil.getRestTemplate().getForEntity(url, String.class, map);
        if (HttpStatus.OK.equals(result.getStatusCode())) {
            String sentMessage = String.format("执行禁言成功。[CQ:at,qq=%s]被禁言%d秒。", bannedId, duration);
            sendGroupMsg(event.getGroupId(), sentMessage);
        }
    }

    @Override
    public void sendGroupMsg(String groupId, String message) {
        String url = robotRequestUrl + "send_group_msg?group_id={group_id}&message={message}";
        Map<String, Object> map = new HashMap<>();
        map.put("group_id", groupId);
        map.put("message", message);
        ResponseEntity<String> result = RemoteObjectUtil.getRestTemplate().getForEntity(url, String.class, map);
        if (HttpStatus.OK.equals(result.getStatusCode())) {
            log.info("发送群消息成功, 群号: {}, 消息体: {}", groupId, message);
        }
    }

    private boolean isRobotAdmin(String userId, String groupId) {
        CqTencentQQMember groupMemberInfo = getGroupMemberInfo(groupId, userId);
        String adminUserIds = String.valueOf(redisUtils.get("goCqHttpRobot:robotAdmin"));
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
        ResponseEntity<CqHttpResponse> result = RemoteObjectUtil.getRestTemplate().getForEntity(url,
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
