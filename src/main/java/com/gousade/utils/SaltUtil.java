package com.gousade.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.jupiter.api.Test;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Slf4j
public class SaltUtil {// toHex和bytetoHex方法得到的结果是相同的，ByteSource.Util.bytes(salt)方法似乎并不影响加密结果
    private static final String g = "-";
    private static final String k = "";
    private static final String algorithmName = "md5";
    private static final int hashIterations = 52;

    public static String generateUUId() {
        return UUID.randomUUID().toString().replace(g, k);
    }

    public static String getUUId() {
        return UUID.randomUUID().toString().replace(g, k);
    }

    public static String getsalt() {
        return DigestUtils.md5Hex(UUID.randomUUID().toString().replace(g, k));
    }

    public static String toHex(Object source, Object salt) {
        return new SimpleHash(algorithmName, source, ByteSource.Util.bytes(salt), hashIterations).toHex();
    }

    public static String bytetoHex(Object source, Object salt) {
        return new SimpleHash(algorithmName, source, salt, hashIterations).toHex();
    }

    @Test
    public void test() {
		/*String saltstr= getsalt();
		String uidstr=getUUId();
		String uuidstr=DigestUtils.md5Hex(uidstr);
		System.out.println("uid："+uidstr);
		System.out.println("uid加盐："+uuidstr);
		System.out.println("盐："+saltstr);
		InetAddress address = InetAddress.getLocalHost();
		String hostAddress = address.getHostAddress();
		System.out.println("your ip is : "+hostAddress);
		String nobyte=toHex("123456","salt");
		String havebyte=toHex("123456","salt");
		System.err.println("无byte加密："+nobyte);
		System.err.println("有byte加密："+havebyte);
		System.err.println(ByteSource.Util.bytes("salt").toString());*/
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -10);
        date = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String dateStr = sdf.format(date);
        log.info("日期：" + dateStr);

        BigDecimal b = new BigDecimal(45.65);
        int a = b.intValue();
        log.info("BigDecimal转int:" + a);
        Calendar rightNow = Calendar.getInstance();
        String[] data = rightNow.getTime().toString().split(" ");
        System.out.println("Today is " + data[0]);
        if (Integer.parseInt(data[2]) % 2 == 0) {
            System.out.println("today is even number.");
        } else {
            System.out.println("today is odd number.");
        }
        log.info("D:" + File.separator + "gousadeFiles" + File.separator);
        log.info(File.separator);
        for (int i = 10; i < 9; i++) {
            log.info(i + "");
        }
        String rootpath = ClassUtils.getDefaultClassLoader().getResource("").getPath();
        log.info("rootpath={}", rootpath);
        log.info("thispath={}", this.getClass().getResource("/").getPath());
        try {
            String classpath = ResourceUtils.getURL("classpath:").getPath();
            log.info("classpath={}", classpath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String dir = System.getProperty("user.dir");
        log.info("dir={}", dir);
        File file = new File("");
        log.info("file={}", file.getAbsolutePath());
        String dateTimeformatter = ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS ZZZZ 'CST'"));
        log.info("ZonedDateTime={}", dateTimeformatter);
        ZonedDateTime defaulTime = ZonedDateTime.now(); // 默认时区
        ZonedDateTime chicagoTime = ZonedDateTime.now(ZoneId.of("America/Chicago")); // 用指定时区获取当前时间
        ZonedDateTime utcTime9 = ZonedDateTime.now(ZoneId.of("UTC+9")); // 用指定时区获取当前时间
        log.info("defaulTime={}", defaulTime);
        log.info("chicagoTime={}", chicagoTime);
        log.info("utcTime9={}", utcTime9);
    }

}
