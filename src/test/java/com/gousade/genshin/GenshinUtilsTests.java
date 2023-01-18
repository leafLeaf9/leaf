package com.gousade.genshin;

import com.alibaba.fastjson.JSONObject;
import com.gousade.entity.dto.GenshinRequestBody;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class GenshinUtilsTests {
    private static String uid;
    private static String server;
    private static String cookie;

    @BeforeAll
    public static void beforeAll() {
        uid = "yourUid";
        server = "cn_gf01";
        cookie = "yourCK";
    }

    @Test
    public void characterApi() {
        GenshinRequestBody body = new GenshinRequestBody(uid, server);
        JSONObject characters = GenshinUtils.listGenshinCharacters(body, cookie);
        JSONObject charactersInShowcase = GenshinUtils.listGenshinCharacters(body, "otherPeopleCK");
        System.out.println(characters);
        System.out.println(charactersInShowcase);
    }
}
