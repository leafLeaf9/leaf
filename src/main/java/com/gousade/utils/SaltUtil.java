package com.gousade.utils;

import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;

public class SaltUtil {//toHex和bytetoHex方法得到的结果是相同的，ByteSource.Util.bytes(salt)方法似乎并不影响加密结果
    public static final String g = "-";
    public static final String k = "";
    private static String algorithmName="md5";
    private static int hashIterations=52;
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
    	String str1="宽带智能提速包自2018年10月17日上市，专门针对临时需要提速的客户推出的一项服务，目前有100M提速包、200M提速包2种，花2元/小时，就可让宽带下载速率最高提速到200M，上传速率最高提速到30M。时长可以累计，时间暂时用不完，可以通过开关来自由控制，30天内使用有效。您可以通过网上营业厅、“欢go” 手机营业厅办理哦！";
    	String str2="宽带智能提速包自2018年10月17日上市，专门针对临时需要提速的客户推出的一项服务，目前有100M提速包、200M提速包2种，花2元/小时，就可让宽带下载速率最高提速到200M，上传速率最高提速到30M。时长可以累计，时间暂时用不完，可以通过开关来自由控制，30天内使用有效。您可以通过网上营业厅、“欢go” 手机营业厅办理哦！";
    	System.out.println(str1.equals(str2));
    }
}
