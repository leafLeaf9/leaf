package com.gousade.util;

public class ChineseUtil {
    private static final String CHINESE_REGEX = "[\\u4e00-\\u9fa5]";

    /**
     * 判断字符串是否包含中文
     *
     * @param str 输入字符串
     * @return true 包含中文，false 不包含
     */
    public static boolean containsChinese(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        return str.matches(".*" + CHINESE_REGEX + ".*");
    }
}
