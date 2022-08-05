package com.gousade.genshin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.gousade.entity.dto.GenshinRequestBody;
import com.gousade.entity.dto.GenshinSign;
import com.gousade.util.JsonUtils;
import com.gousade.util.RemoteObjectUtil;
import com.gousade.util.SaltUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class GenshinUtils {
    public static JSONObject listGenshinCharacters(GenshinRequestBody body, String cookie) {
        String url = MiHoYoConstant.HOST_RECORD + MiHoYoConstant.GAME_RECORD + "character";
        String str = JsonUtils.serialize(body);
        String ds = getGenshinUserInfoDS(new HashMap<>(), str);
        RestTemplate restTemplate = getMiHoYoRestTemplate(ds, cookie, getGenshinUserInfoAppVersion(), getGenshinClientType());
        ResponseEntity<JSONObject> result = restTemplate.postForEntity(url, body, JSONObject.class);
        return result.getBody();
    }

    public static JSONObject getGenshinUserInfo(String uid, String cookie) {
        String url = MiHoYoConstant.INFO_URL;
        GenshinSign body = new GenshinSign();
        body.setUid(uid);
        body.setUid(uid);
        Map<String, String> paramMap = new TreeMap<>();
        paramMap.put("server", String.valueOf(MiHoYoConstant.SERVER_REGION_MAP
                .getOrDefault(String.valueOf(uid.charAt(0)), "cn_gf01")));
        paramMap.put("role_id", uid);
        String ds = getGenshinUserInfoDS(paramMap, StringPool.EMPTY);
        RestTemplate restTemplate = getMiHoYoRestTemplate(ds, cookie, getGenshinUserInfoAppVersion(), getGenshinClientType());
        ResponseEntity<JSONObject> result = restTemplate.getForEntity(url, JSONObject.class, paramMap);
        return result.getBody();
    }

    public static RestTemplate getMiHoYoRestTemplate() {
        return getMiHoYoRestTemplate(getDS(), "", getGenshinAppVersion(), getGenshinClientType());
    }

    public static RestTemplate getMiHoYoRestTemplate(String ds, String cookie, String appVersion, String clientType) {
        RestTemplate restTemplate = RemoteObjectUtil.getRestTemplate();
        Map<String, String> headerMap = getBasicHeaderMap();
        headerMap.put("x-rpc-device_id", SaltUtil.generateUUId().toUpperCase());
        headerMap.put("x-rpc-client_type", clientType);
        headerMap.put("x-rpc-app_version", appVersion);
        headerMap.put("User-Agent", " miHoYoBBS/" + appVersion);
        headerMap.put("DS", ds);
        headerMap.put("Cookie", cookie);
        RemoteObjectUtil.addHeaders(restTemplate, headerMap);
        return restTemplate;
    }

    public static Map<String, String> getBasicHeaderMap() {
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("User-Agent", String.format(MiHoYoConstant.USER_AGENT_TEMPLATE, MiHoYoConstant.GENSHIN_USER_INFO_APP_VERSION));
        headerMap.put("Referer", MiHoYoConstant.REFERER_URL);
//        headerMap.put("Accept-Encoding", "gzip, deflate, br");
        headerMap.put("x-rpc-channel", "appstore");
        headerMap.put("accept-language", "zh-CN,zh;q=0.9,ja-JP;q=0.8,ja;q=0.7,en-US;q=0.6,en;q=0.5");
//        headerMap.put("accept-encoding", "gzip, deflate");
        headerMap.put("x-requested-with", "com.mihoyo.hyperion");
        headerMap.put("Host", "api-takumi.mihoyo.com");
        return headerMap;
    }

    public static JSONObject getGenshinUserGameRoles(String cookie) {
        String url = MiHoYoConstant.ROLE_URL;
        String ds = getGenshinUserInfoDS(new HashMap<>(), StringPool.EMPTY);
        RestTemplate restTemplate = getMiHoYoRestTemplate(ds, cookie, getGenshinUserInfoAppVersion(), getGenshinClientType());
        ResponseEntity<JSONObject> result = restTemplate.getForEntity(url, JSONObject.class);
        return result.getBody();
    }

    public static void main(String[] args) {
        String cookie = "";
        JSONObject roles = getGenshinUserGameRoles(cookie);
        JSONArray jsonArray = roles.getJSONObject("data").getJSONArray("list");
        for (Object e : jsonArray) {
            JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(e));
            JSONObject userInfo = getGenshinUserInfo(jsonObject.getString("game_uid"), cookie);
            GenshinSign genshinSign = new GenshinSign(MiHoYoConstant.SIGN_ACT_ID, jsonObject.getString("region"),
                    jsonObject.getString("game_uid"));
            GenshinRequestBody body = new GenshinRequestBody(jsonObject.getString("game_uid"), jsonObject.getString("region"));
            JSONObject characters = listGenshinCharacters(body, cookie);
            System.out.println(userInfo);
            System.out.println(characters);
            System.out.println(characters.getJSONObject("data").getJSONArray("avatars").toString());
            genshinSign(genshinSign, cookie);
        }
        System.out.println(roles);
    }

    public static JSONObject genshinSign(GenshinSign genshinSign, String cookie) {
        String url = MiHoYoConstant.SIGN_URL;
        String ds = getDS();
        RestTemplate restTemplate = getMiHoYoRestTemplate(ds, cookie, getGenshinAppVersion(), getGenshinClientType());
        ResponseEntity<JSONObject> result = restTemplate.postForEntity(url, genshinSign, JSONObject.class);
        return result.getBody();
    }

    public static JSONObject getGenshinSignInfo(GenshinSign genshinSign, String cookie) {
        String url = MiHoYoConstant.SIGN_INFO_URL;
        Map<String, String> paramMap = new TreeMap<>();
        paramMap.put("act_id", genshinSign.getActId());
        paramMap.put("region", genshinSign.getRegion());
        paramMap.put("uid", genshinSign.getUid());
        String ds = getDS();
        RestTemplate restTemplate = getMiHoYoRestTemplate(ds, cookie, getGenshinAppVersion(), getGenshinClientType());
        ResponseEntity<JSONObject> result = restTemplate.getForEntity(url, JSONObject.class, paramMap);
        return result.getBody();
    }

    public static String getGenshinUserInfoDS(Map<String, String> paramMap, String body) {
        String query = paramMap.entrySet().stream().map(e -> e.getKey() + "=" + e.getValue())
                .collect(Collectors.joining("&"));
        long time = System.currentTimeMillis() / 1000;
        String random = createRandomString();
        String check = String.format("salt=%s&t=%s&r=%s&b=%s&q=%s", getGenShinUserInfoSalt(), time, random, body, query);
        String checkMd5 = DigestUtils.md5Hex(check);
        return String.format("%s,%s,%s", time, random, checkMd5);
    }


    public static String getDS() {
        long time = System.currentTimeMillis() / 1000;
        String random = createRandomString();
        String check = String.format("salt=%s&t=%s&r=%s", getGenShinSalt(), time, random);
        String checkMd5 = DigestUtils.md5Hex(check);
        return String.format("%s,%s,%s", time, random, checkMd5);
    }

    private static String createRandomString() {
//        return String.valueOf(100000 + new Random().nextInt(100000));
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 6; i++) {
            String constants = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
            int number = random.nextInt(constants.length());
            char charAt = constants.charAt(number);
            sb.append(charAt);
        }
        return sb.toString();
    }

    public static String getGenShinSalt() {
        return MiHoYoConstant.GENSHIN_SALT;
    }

    public static String getGenShinUserInfoSalt() {
        return MiHoYoConstant.GENSHIN_USER_INFO_SALT;
    }

    public static String getGenshinUserInfoAppVersion() {
        return MiHoYoConstant.GENSHIN_USER_INFO_APP_VERSION;
    }

    public static String getGenshinAppVersion() {
        return MiHoYoConstant.GENSHIN_APP_VERSION;
    }

    public static String getCommunityAppVersion() {
        return MiHoYoConstant.COMMUNITY_APP_VERSION;
    }

    public static String getGenshinClientType() {
        return MiHoYoConstant.GENSHIN_CLIENT_TYPE;
    }

    public static String getCommunityClientType() {
        return MiHoYoConstant.COMMUNITY_CLIENT_TYPE;
    }
}
