package com.gousade.genshin;

import java.util.HashMap;
import java.util.Map;

public class MiHoYoConstant {
    public static final Map<String, Object> SERVER_REGION_MAP = new HashMap<String, Object>() {{
        put("1", "cn_gf01");//官服
        put("2", "cn_gf01");//官服
        put("5", "cn_qd01");//B服
        put("6", "os_usa");//此行及以下都是外服
        put("7", "os_euro");
        put("8", "os_asia");
        put("9", "os_cht");
    }};
    public static final String HOST = "https://api-takumi.mihoyo.com";
    public static final String HOST_RECORD = "https://api-takumi-record.mihoyo.com";
    public static final String GAME_RECORD = "/game_record/app/genshin/api/";
    public static final String GENSHIN_APP_VERSION = "2.28.1";
    public static final String COMMUNITY_APP_VERSION = "2.8.0";
    public static final String GENSHIN_USER_INFO_APP_VERSION = "2.37.1";
    public static final String GENSHIN_CLIENT_TYPE = "5";
    public static final String COMMUNITY_CLIENT_TYPE = "2";
    public static final String SIGN_ACT_ID = "e202009291139501";
    public static final String GENSHIN_USER_INFO_SALT = "xV8v4Qu54lUKrEYFZkJhB8cuOh9Asafs";
    public static final String GENSHIN_SALT = "ulInCDohgEs557j0VsPDYnQaaz6KJcv5";
    public static final String COMMUNITY_SALT = "dWCcD2FsOUXEstC5f9xubswZxEeoBOTc";
    public static final String USER_AGENT_TEMPLATE = "Mozilla/5.0 (iPhone; CPU iPhone OS 14_0_1 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) miHoYoBBS/%s";
    public static final String REFERER_URL = String.format("https://webstatic.mihoyo.com/bbs/event/signin-ys/index.html?bbs_auth_required=%s&act_id=%s&utm_source=%s&utm_medium=%s&utm_campaign=%s", true, SIGN_ACT_ID, "bbs", "mys", "icon");
    public static final String INFO_URL = "https://api-takumi-record.mihoyo.com/game_record/app/genshin/api/index?server={server}&role_id={role_id}";
    public static final String ROLE_URL = String.format("https://api-takumi.mihoyo.com/binding/api/getUserGameRolesByCookie?game_biz=%s", "hk4e_cn");
    public static final String SIGN_INFO_URL = "https://api-takumi.mihoyo.com/event/bbs_sign_reward/info?region={region}&act_id={act_id}&uid={uid}";
    public static final String SIGN_URL = "https://api-takumi.mihoyo.com/event/bbs_sign_reward/sign";
    public static final String AWARD_URL = String.format("https://api-takumi.mihoyo.com/event/bbs_sign_reward/home?act_id=%s", SIGN_ACT_ID);

}
