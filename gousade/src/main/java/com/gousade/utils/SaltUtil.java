package com.gousade.utils;

import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

public class SaltUtil {
    public static final String g = "-";
    public static final String k = "";
    public static String getUUId() {
        return UUID.randomUUID().toString().replace(g, k);
    }
    
    public static String getsalt() {
        return DigestUtils.md5Hex(UUID.randomUUID().toString().replace(g, k));
    }
    @Test
    public void test() {
        String saltstr= getsalt();
        String uidstr=getUUId();
        String uuidstr=DigestUtils.md5Hex(uidstr);
        System.out.println("uid："+uidstr);
        System.out.println("uid加盐："+uuidstr);
        System.out.println("盐："+saltstr);
    }
}
