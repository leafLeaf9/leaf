package com.gousade.utils;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.gousade.jasypt.JasyptUtil;
import com.gousade.mapper.SmsResponseLogMapper;
import com.gousade.pojo.SmsResponseLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created on 17/6/7.
 * 短信API产品的DEMO程序,工程中包含了一个SmsDemo类，直接通过
 * 执行main函数即可体验短信产品API功能(只需要将AK替换成开通了云通信-短信产品功能的AK即可)
 * 工程依赖了2个jar包(存放在工程的libs目录下)
 * 1:aliyun-java-sdk-core.jar
 * 2:aliyun-java-sdk-dysmsapi.jar
 *
 * 备注:Demo工程编码采用UTF-8
 * 国际短信发送请勿参照此DEMO
 */
@Slf4j
@Component
public class SendSmsUtil {
	
	@Resource
    private JasyptUtil jasyptUtil;
	
	@Autowired
	private SmsResponseLogMapper smsResponseLogMapper;

    //产品名称:云通信短信API产品,开发者无需替换
    static final String product = "Dysmsapi";
    //产品域名,开发者无需替换
    static final String domain = "dysmsapi.aliyuncs.com";

    static final String accessKeyId = "3r+jh0UcQMkX9SJ9U8ble7Ms75W+mMJz9HkigkJvwS81jsNLgsy6LCujmmP57Kr8HVCkNIP4d/Bz5Dv1ZkgHhQ==";
    static final String accessKeySecret = "PWwlMV42eZWkghZvThz/uX/4lTEruJRVVvIQqEVO9/6Pr0REqZu+tf32dMN7pg1BgwfWIMHX73D7n+AAQ0mQpQ==";

    public void sendSms(String mobile,int code) {
        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou",
        		jasyptUtil.decypt(accessKeyId), jasyptUtil.decypt(accessKeySecret));
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", mobile);
        request.putQueryParameter("SignName", "GisardLTE");
        request.putQueryParameter("TemplateCode", "SMS_200721670z");
        request.putQueryParameter("TemplateParam", "{\"code\":"+code+"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            log.info("aliyun sendSms response: "+response.getData());
            SmsResponseLog entity = SmsResponseLog.builder().response(response.getData()).build();
            smsResponseLogMapper.insert(entity);
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
    
}
