package com.gousade.utils;

import com.gousade.entity.QRCodeDTO;
import com.gousade.entity.WeChatTokenInfo;
import com.gousade.entity.WeChatUserInfo;
import com.gousade.redis.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author woxigousade
 * @date 2020/11/5
 */
@Slf4j
@Component
public class WeChatUtils {
    private static final String WECHAT_LOGIN_STATE_PREFIX = "login:wechat:state:";
    private static final String WECHAT_USER_CONFIG_PARAM_NAME = "wechatUnionId";
    private static final int DEFAULT_EXPIRE_TIME = 300;
    @Value("${wechat.web.app.id:appId}")
    private String wechatWebAppId;
    @Value("${wechat.web.secret:secret}")
    private String wechatWebSecret;
    @Value("${wechat.redirect.uri:gousade.com}")
    private String wechatRedirectUri;
    @Value("${wechat.web.login.scope:snsapi_login}")
    private String wechatWebLoginScope;
    @Resource
    private RedisUtil redisUtil;
    @Autowired
    private HttpServletRequest request;

    public String getQRCodeUrl() throws UnsupportedEncodingException {
        String url = "https://open.weixin.qq.com/connect/qrconnect?appid=appId"
                + "&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
        String redirectUri = URLEncoder.encode(wechatRedirectUri, StandardCharsets.UTF_8.name());
        String state = UUID.randomUUID().toString().replaceAll("-", "");
        cacheState(state);
        return url.replace("appId", wechatWebAppId).replace("REDIRECT_URI", redirectUri)
                .replace("SCOPE", wechatWebLoginScope).replace("STATE#wechat_redirect", state);
    }

    private void cacheState(String state) {
        redisUtil.set(WECHAT_LOGIN_STATE_PREFIX + request.getSession().getId(), state, DEFAULT_EXPIRE_TIME);
    }

    public QRCodeDTO getQRCodeDTO() throws UnsupportedEncodingException {
        String redirectUri = URLEncoder.encode(wechatRedirectUri, StandardCharsets.UTF_8.name());
        String state = UUID.randomUUID().toString().replaceAll("-", "");
        cacheState(state);
        return new QRCodeDTO(wechatWebAppId, redirectUri, wechatWebLoginScope, state);
    }

    public void wechatLogin(String code, String state, String loginType) {
        checkSate(state);

        WeChatTokenInfo tokenInfo = getWeChatTokenInfo(code);
        refreshToken(tokenInfo.getRefresh_token());
        WeChatUserInfo userInfo = getUserInfoByToken(tokenInfo);
        String unionId = userInfo.getUnionid();
        //TODO 登录逻辑
    }

    private WeChatTokenInfo getWeChatTokenInfo(String code) {
        return getWeChatAccessToken(code, wechatWebAppId, wechatWebSecret);
    }

    public WeChatTokenInfo getWeChatAccessToken(String code, String appId, String secret) {
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token"
                + "?appid=appId&secret=SECRET&code=CODE&grant_type=authorization_code";
        Map<String, Object> param = new HashMap<>();
        param.put("appId", appId);
        param.put("SECRET", secret);
        param.put("CODE", code);
        RestTemplate restTemplate = RemoteObjectUtil.getTextSupportedRestTemplate();
        WeChatTokenInfo tokenInfo = restTemplate.getForObject(url, WeChatTokenInfo.class, param);
        if (!ObjectUtils.isEmpty(Objects.requireNonNull(tokenInfo).getErrcode())) {
            throw new RuntimeException("微信登录-获取access_token失败：" + tokenInfo.getErrmsg());
        }
        return tokenInfo;
    }

    private void checkSate(String state) {
        String key = WECHAT_LOGIN_STATE_PREFIX + request.getSession().getId();
        String cacheState = String.valueOf(redisUtil.get(key));
        if (ObjectUtils.isEmpty(cacheState) || !cacheState.equals(state)) {
            throw new RuntimeException("微信登录-state校验失败。");
        }
        redisUtil.del(key);
    }

    public void refreshToken(String refreshToken) {
        String url = "https://api.weixin.qq.com/sns/oauth2/refresh_token"
                + "?appid={appId}&grant_type=refresh_token&refresh_token={refreshToken}";
        Map<String, Object> param = new HashMap<>();
        param.put("appId", wechatWebAppId);
        param.put("refreshToken", refreshToken);
        RestTemplate restTemplate = RemoteObjectUtil.getTextSupportedRestTemplate();
        WeChatTokenInfo refreshTokenInfo = restTemplate.getForObject(url, WeChatTokenInfo.class, param);
        if (!ObjectUtils.isEmpty(Objects.requireNonNull(refreshTokenInfo).getErrcode())) {
            log.error("微信登录-刷新access_token失败：" + refreshTokenInfo.getErrmsg());
            return;
        }
        log.info("微信登录-刷新access_token成功，token {} 剩余有效期为：{}小时。",
                refreshTokenInfo.getAccess_token(), TimeUnit.SECONDS.toHours(refreshTokenInfo.getExpires_in()));
    }

    private WeChatUserInfo getUserInfoByToken(WeChatTokenInfo tokenInfo) {
        String url = "https://api.weixin.qq.com/sns/userinfo?access_token={accessToken}&openid={openId}";
        Map<String, Object> param = new HashMap<>();
        param.put("accessToken", tokenInfo.getAccess_token());
        param.put("openId", tokenInfo.getOpenid());
        RestTemplate restTemplate = RemoteObjectUtil.getTextSupportedRestTemplate();
        WeChatUserInfo userInfo = restTemplate.getForObject(url, WeChatUserInfo.class, param);
        if (!ObjectUtils.isEmpty(Objects.requireNonNull(userInfo).getErrcode())) {
            throw new RuntimeException("微信登录-获取用户信息失败：{}" + userInfo.getErrmsg());
        }
        log.info("微信登录-获取到用户信息：{}", userInfo);
        return userInfo;
    }
}
