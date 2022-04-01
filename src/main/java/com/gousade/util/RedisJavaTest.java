package com.gousade.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

@Slf4j
public class RedisJavaTest {
    @Test
    public void RedisStringAndHashTest() {
        // Connecting to Redis server on localhost
        Jedis jedis = new Jedis("localhost");
        log.info("Connection to server sucessfully");
        // set the data in redis string
        jedis.set("gousadekey", "gisard");
        // Get the stored data and print it
        System.out.println("Stored string in redis:: " + jedis.get("gousadekey"));
        jedis.hset("gousademap", "firstkey", "valueoffirstkey");
        jedis.hset("gousademap", "secondKey", "valueOfSecondKey");
        log.info("Stored hash in redis:: " + jedis.hgetAll("gousademap"));
        jedis.close();
    }

    @Test
    public void RedisListtest() {
        // 连接本地的 Redis 服务
        Jedis jedis = new Jedis("localhost");
        log.info("连接成功");
        // 存储数据到列表中
        jedis.lpush("newlist", "gisard1");
        jedis.lpush("newlist", "gisard2");
        jedis.lpush("newlist", "gisard3");
        // 获取存储的数据并输出
        List<String> list = jedis.lrange("newlist", 0, 2);
        for (int i = 0; i < list.size(); i++) {
            log.info("列表项为: " + list.get(i));
        }
        jedis.close();
    }

    /**
     * keys pattern 命令：查找所有符合给定模式 pattern 的 key 。 KEYS * 匹配数据库中所有 key 。 KEYS h?llo
     * 匹配 hello ， hallo 和 hxllo 等。 KEYS h*llo 匹配 hllo 和 heeeeello 等。 KEYS h[ae]llo
     * 匹配 hello 和 hallo ，但不匹配 hillo 。
     */
    @Test
    public void RedisKeytest() {
        // 连接本地的 Redis 服务
        Jedis jedis = new Jedis("localhost");
//		jedis.auth("mypass"); //一般来说要配置IP,端口号,密码,此处本地服务器是没有密码的  public String auth(final String password)
        log.info("连接成功");
        // 获取数据并输出
        Set<String> keys = jedis.keys("*a*");
        Iterator<String> it = keys.iterator();
        while (it.hasNext()) {
            String key = it.next();
            log.info(key);
        }
        jedis.close();
    }

    /**
     * 可以简单的理解在 Java 的正则表达式中，两个 \\ 代表其他语言中的一个 \，这也就是为什么表示一位数字的正则表达式是
     * \\d，而表示一个普通的反斜杠是 \\\\。
     * 标准郑泽中\d代表数字字符，在java中就需要写成\\d，所以只要把标准正则中的所有\都变成\\就可以在java中同义
     */
    @SuppressWarnings("unused")
    @Test
    public void patterntest() {
        String content = "Swift1 Swift2 Swift3Swift4Swift4Swift4";
        String pattern = "Swift(?!4)"; // .* .代表任意一个字符 *代表*前的那个字符可以重复0到无数次 所以.*代表任意个任意字符
        boolean isMatch = Pattern.matches(pattern, content);
        log.warn("字符串中是否包含了 'runoob' 子字符串? " + isMatch);
        String str = "()";
        String pat = "\\(.*";
//		 log.debug(""+Pattern.matches(pat, str));
    }
}
