package com.gousade.utils;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class MD5Utils {//这个MD5Utils类作用是对密码进行52次MD5加密，作用与SaltUtil中的toHex方法一样，在注册时采用SaltUtil，未用到此类
	
	 private static String algorithmName = "md5";   //指定散列算法为MD5,还有别的算法如：SHA256、SHA1、SHA512
	 private static int hashIterations = 52;     //散列迭代次数 md5(md5(pwd)): new Md5Hash(pwd, salt, 2).toString()
	  
    //加密：输入明文得到密文
    public static String encodePassword(String pwd, String salt) {
        //user.setSalt(randomNumberGenerator.nextBytes().toHex());
        String newPassword = new SimpleHash(
                algorithmName,
                pwd,
                ByteSource.Util.bytes(salt),
                hashIterations).toHex();
        return newPassword;
    }
}
