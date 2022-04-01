package com.gousade.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import java.util.UUID;

@Slf4j
public class SaltUtil {
    private static final String MINUS_SIGN = "-";
    private static final String EMPTY = "";
    private static final String ALGORITHM_NAME = "md5";
    private static final int HASH_ITERATIONS = 52;

    public static String generateUUId() {
        return UUID.randomUUID().toString().replace(MINUS_SIGN, EMPTY);
    }

    public static String getUUId() {
        return UUID.randomUUID().toString().replace(MINUS_SIGN, EMPTY);
    }

    public static String toHex(Object source, Object salt) {
        return new SimpleHash(ALGORITHM_NAME, source, ByteSource.Util.bytes(salt), HASH_ITERATIONS).toHex();
    }
}
