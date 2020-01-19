package com.gousade.utils;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;
/**
 * 
 * des加密解密
 * @author gzc
 *
 */
public class DesUtil {
  
	    /**
	     * 获得秘钥
	     * @return
	     * @throws InvalidKeySpecException 
	     */
	    public static Key getDesKey() {
	        //1.初始化key秘钥   
	    	Key key=null;
			try {
				 KeyGenerator keyGenerator = KeyGenerator.getInstance("DESede");  
		            keyGenerator.init(new SecureRandom());  
		            SecretKey secretKey= keyGenerator.generateKey();  
		            //转换key秘钥  
		            DESedeKeySpec deSedeKeySpec=new DESedeKeySpec(secretKey.getEncoded());  
		            SecretKeyFactory secretKeyFactory=SecretKeyFactory.getInstance("DESede");  
		            key= secretKeyFactory.generateSecret(deSedeKeySpec);  
			} catch (Exception e) {
				e.printStackTrace();
			}  
	        return key;
	    }
	    
	    /**
	     * 对秘钥进行编码保存
	     * @param key
	     * @return
	     */
	    public static String getHexKey(Key key){
	        return HexBin.encode(key.getEncoded());
	    }  
	    
	    /**
	     * 加密float類型
	     * @param key 秘钥
	     * @param f 
	     * @return
	     */
	    public static String desEncode(Key key,float f){
	    	String desEncodeStr=null;
	        try {  
	            Cipher cipher=Cipher.getInstance("DESede/ECB/PKCS5Padding");  
	            cipher.init(Cipher.ENCRYPT_MODE, key);  
	            byte[] result= cipher.doFinal((f+"").getBytes());
	            desEncodeStr=HexBin.encode(result);
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        } 
	        return desEncodeStr;
	    }
	    
	    /**
	     * 通过16位的编码秘钥，反推秘钥
	     * @param hexKey
	     * @return
	     */
	    public static Key getDesKey(String hexKey){  
	    	Key key=null;
	        try {  
	        	//解码，重新生成key
	            byte[] keybyte= HexBin.decode(hexKey);  
	        	DESedeKeySpec deSedeKeySpec=new DESedeKeySpec(keybyte);  
	            SecretKeyFactory secretKeyFactory=SecretKeyFactory.getInstance("DESede");  
	            key= secretKeyFactory.generateSecret(deSedeKeySpec); //获取到key秘钥  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        } 
	        return key;
	    }
	    
	    
	    /**
	     * 解密
	     * @param key  秘钥
	     * @param encodeStr 需要解码的字符串
	     * @return
	     */
	    public static String desDecode(Key key,String encodeStr){  
	    	String resultStr=null;
	        try {  
	        //进行解密  
	            Cipher cipher=Cipher.getInstance("DESede/ECB/PKCS5Padding");  
	            cipher.init(Cipher.DECRYPT_MODE, key);  
	            byte[] result = cipher.doFinal(HexBin.decode(encodeStr));  
	            resultStr=new String(result);
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        } 
	        return resultStr;
	    }
	    
	    
	  /*  public static void main(String[] args) {
	    	//1、得到秘钥
			Key key = getDesKey();
			//2、得到编码的秘钥
			String hexKey = getHexKey(key);
			System.out.println("编码的秘钥：      "+hexKey);
			
			float f1=5012.53f;
			float f2=525.00f;
			//3、利用秘钥去加密数据
			String desEncode1 = desEncode(key, f1);
			String desEncode2 = desEncode(key, f2);
			System.out.println(f1+"   加密后的数据：   "+desEncode1);
			System.out.println(f2+"  加密后的数据：   "+desEncode2);
			
			//4、利用编码的秘钥   得到秘钥
			Key desKey = getDesKey(hexKey);
			
			//5、利用key  解密数据
			String desDecode1 = desDecode(desKey,desEncode1);
			String desDecode2 = desDecode(desKey,desEncode2);
			
			System.out.println(f1+"  解密后的数据：      "+desDecode1);
			System.out.println(f2+"  解密后的数据：      "+desDecode2);
	    	
		}*/
	    
}
