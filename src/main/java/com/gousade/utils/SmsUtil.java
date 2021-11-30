package com.gousade.utils;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.gousade.jasypt.JasyptUtil;
import com.gousade.mapper.SmsResponseLogMapper;
import com.gousade.pojo.SmsResponseLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created on 17/6/7. 短信API产品的DEMO程序,工程中包含了一个SmsDemo类，直接通过
 * 执行main函数即可体验短信产品API功能(只需要将AK替换成开通了云通信-短信产品功能的AK即可) 工程依赖了2个jar包(存放在工程的libs目录下)
 * 1:aliyun-java-sdk-core.jar 2:aliyun-java-sdk-dysmsapi.jar
 * <p>
 * 备注:Demo工程编码采用UTF-8 国际短信发送请勿参照此DEMO
 */
@Slf4j
@Component
public class SmsUtil {

    /*private static final String accessKeyId = "M6o8JNrzxIO7zI2S4LrWRdaNdCNACo9/1v5/DqfDdFJEb3MlrXU+2aJi2oRxieUDQKCCIC2rJvw6HDVR9zEdgQ==";
    private static final String accessKeySecret = "bFfybIAXktU8bitG/bRkA36bEHW/RcNBuheCoJeEzG8nz8UgTaNYWEilha5sMiKuF/k/1IRKn88xh+0g4x09pw==";*/
    private static final String SIGN_NAME = "HrhfSLEriq7HCvkMG88vhQ60AJ9U5mR6GtFRoKVHj9XGdJgrSuvvQL/MNEoiFsPD";
    private static final String TEMPLATE_CODE = "JP+XAHjZfZW1a1gb4ugXGqHwr80IQxbFKma0pzt54oQoMC66Srm+roFA68b6wLyF";
    private static String accessKeyId;
    private static String accessKeySecret;
    @Resource
    private JasyptUtil jasyptUtil;
    @Autowired
    private SmsResponseLogMapper smsResponseLogMapper;

    @Value("${aliyun.accessKeyId}")
    public void setAccessKeyId(String accessKeyId) {
        SmsUtil.accessKeyId = accessKeyId;
    }

    @Value("${aliyun.accessKeySecret}")
    public void setAccessKeySecret(String accessKeySecret) {
        SmsUtil.accessKeySecret = accessKeySecret;
    }

    public void sendSms(String mobile, int code) {
        // 可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
		/*IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", jasyptUtil.decrypt(accessKeyId),
		        jasyptUtil.decrypt(accessKeySecret));*/
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", mobile);
        request.putQueryParameter("SignName", jasyptUtil.decrypt(SIGN_NAME));
        request.putQueryParameter("TemplateCode", jasyptUtil.decrypt(TEMPLATE_CODE));
        request.putQueryParameter("TemplateParam", "{\"code\":" + code + "}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            log.info("aliyun sendSms response: " + response.getData());
            SmsResponseLog entity = SmsResponseLog.builder().response(response.getData()).build();
            smsResponseLogMapper.insert(entity);
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

}
