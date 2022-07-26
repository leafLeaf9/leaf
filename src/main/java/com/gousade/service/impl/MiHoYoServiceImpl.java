package com.gousade.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.gousade.entity.dto.CqHttpEvent;
import com.gousade.entity.dto.GenshinAward;
import com.gousade.entity.dto.GenshinSign;
import com.gousade.genshin.GenshinUtils;
import com.gousade.genshin.MiHoYoConstant;
import com.gousade.redis.RedisUtils;
import com.gousade.service.MiHoYoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class MiHoYoServiceImpl implements MiHoYoService {
    public static final String MI_HO_YO_COOKIE_KEY_PREFIX = "miHoYo:cookie:";

    @Resource
    private RedisUtils redisUtils;

    @Override
    public void bindMiHoYoCookie(CqHttpEvent event) {
        String[] split = event.getMessage().split("绑定米游社cookie ");
        redisUtils.set(MI_HO_YO_COOKIE_KEY_PREFIX + event.getUserId(), split[1], 86400 * 365);
        log.info("{}绑定米游社cookie成功，保存到redis缓存。cookie为:{}", event.getUserId(), split[1]);
    }

    @Override
    public String doSign(String cookie) {
        StringBuilder resultMessage = new StringBuilder();
        JSONObject roles = GenshinUtils.getGenshinUserGameRoles(cookie);
        if (roles.getInteger("retcode") != 0) {
            resultMessage.append(roles.getString("message"));
            return resultMessage.toString();
        }
        JSONArray jsonArray = roles.getJSONObject("data").getJSONArray("list");
        for (Object e : jsonArray) {
            JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(e));
            GenshinSign genshinSign = new GenshinSign(MiHoYoConstant.SIGN_ACT_ID, jsonObject.getString("region"),
                    jsonObject.getString("game_uid"));
            JSONObject signResult = GenshinUtils.genshinSign(genshinSign, cookie);
            String message = signResult.getString("message");
            resultMessage.append(jsonObject.getString("game_uid")).append(" ").append(message).append("\n");

            JSONObject signInfo = GenshinUtils.getGenshinSignInfo(genshinSign, cookie);
            resultMessage.append(getSignInfoMessage(signInfo));
        }
        return resultMessage.toString();
    }

    public String getSignInfoMessage(JSONObject signInfoResult) {
        if (signInfoResult == null || signInfoResult.getJSONObject("data") == null) {
            return null;
        }
        LocalDateTime time = LocalDateTime.now();
        Boolean isSign = signInfoResult.getJSONObject("data").getBoolean("is_sign");
        Integer totalSignDay = signInfoResult.getJSONObject("data").getInteger("total_sign_day");
        int day = isSign ? totalSignDay : totalSignDay + 1;
        GenshinAward award = getAwardInfo(day);
        return String.format("%s月已签到%s天, %s签到获取%s * %s", time.getMonth().getValue(),
                totalSignDay, signInfoResult.getJSONObject("data").get("today"), award.getName(), award.getCnt());
    }

    public GenshinAward getAwardInfo(int day) {
        RestTemplate restTemplate = GenshinUtils.getMiHoYoRestTemplate();
        ResponseEntity<JSONObject> responseEntity = restTemplate.getForEntity(MiHoYoConstant.AWARD_URL, JSONObject.class);
        JSONObject awardResult = responseEntity.getBody();
        JSONArray jsonArray = Objects.requireNonNull(awardResult).getJSONObject("data").getJSONArray("awards");
        List<GenshinAward> awards = JSON.parseObject(JSON.toJSONString(jsonArray), new TypeReference<List<GenshinAward>>() {
        });
        return awards.get(day - 1);
    }

}
