package com.gousade.utils;

import java.io.File;
import java.math.BigDecimal;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SaltUtil {//toHex和bytetoHex方法得到的结果是相同的，ByteSource.Util.bytes(salt)方法似乎并不影响加密结果
    public static final String g = "-";
    public static final String k = "";
    private static String algorithmName="md5";
    private static int hashIterations=52;
    
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
    	Date date=new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -10);
		date = calendar.getTime();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
		String dateStr=sdf.format(date);
		log.info("日期："+dateStr);

		BigDecimal b=new BigDecimal(45.65);
		int a = b.intValue();
		log.info("BigDecimal转int:"+a);
		Calendar rightNow=Calendar.getInstance();
		String[] data = rightNow.getTime().toString().split(" ");
		System.out.println("Today is "+ data[0]);
		if(Integer.parseInt(data[2].toString())%2==0){
			System.out.println("today is even number.");
		}else{
			System.out.println("today is odd number.");
		}
		log.info("D:"+File.separator+"gousadeFiles"+File.separator);
		log.info(File.separator);
		for(int i=10;i<9;i++) {
			log.info(i+"");
		}
    }

}
