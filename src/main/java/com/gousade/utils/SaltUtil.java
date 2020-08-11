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
    public void test() throws UnknownHostException {
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
    	/*double otherNum=Math.ceil(5/(7/2));
    	double test =5/3;
    	double test1 =7/2;
    	System.out.println("otherNum："+otherNum);
    	System.out.println("test："+test);
    	System.out.println("test1："+test1);
    	BigDecimal five = new BigDecimal(String.valueOf(5));
    	BigDecimal seven = new BigDecimal(String.valueOf(7));
    	BigDecimal two = new BigDecimal(String.valueOf(2));
    	BigDecimal bigresult=five.divide(seven.divide(two, 2, BigDecimal.ROUND_HALF_UP), 2, BigDecimal.ROUND_HALF_UP);
    	int result=(int) Math.ceil(bigresult.doubleValue());
    	System.out.println("result："+result);*/
    	String str1="亲，您可以通过点击这里进行宽带线路检测，你的宽带出现了以下哪种问题，请选择：[1]自助排障宝典. [2]无法上网. [3]网速慢. [4]经常断线. [5]WiFi上网问题 .";
    	String str2="亲，您可以通过点击这里进行宽带线路检测，你的宽带出现了以下哪种问题，请选择：[1]自助排障宝典. [2]无法上网. [3]网速慢. [4]经常断线. [5]WiFi上网问题 .";
    	System.out.println(str1.equals(str2));
    	String[] zone={"话务运营组","话务预备组","工单投诉组","客服支撑组","无","",null};
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
    }

}
