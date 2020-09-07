package com.gousade.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;

import com.gousade.commonutils.ResponseResult;
import com.gousade.mail.MailManage;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

/**
 * @author woxigsd@gmail.com
 * @date 2020-9-4 16:36:49
 * @description 
 */
@Api(tags="Mail")
@Slf4j
@RestController
@RequestMapping(value = "/mail")
public class MailController {
	
	@Autowired
    private MailManage mailManage;
	
	@RequestMapping(value="/mailSend",method=RequestMethod.GET)
	public ResponseResult mailSend() {
		mailManage.sendSimpleMail("sendtoqq@qq.com","test simple mail"," hello this is simple mail");
		log.info("发送消息成功。");
		return ResponseResult.renderSuccess().message("发送消息成功。");
	}
	
	@RequestMapping(value="/htmlMailSend",method=RequestMethod.GET)
	public ResponseResult htmlMailSend() {
		String htmlContent = "<body style=\"text-align: center;margin-left: auto;margin-right: auto;\">\n"
                + "	<div id=\"welcome\" style=\"text-align: center;position: absolute;\" >\n"
                +"		<h3>欢迎使用GisardAdminLTE - SpringBoot</h3>\n"
                +"		<span>https://github.com/woxigousade/gousade</span>"
                + "		<div\n"
                + "			style=\"text-align: center; padding: 10px\">"
                + "<a style=\"text-decoration: none;\" href=\"https://github.com/woxigousade/gousade\" target=\"_bank\" >"
                + "<strong>GisardAdminLTE - SpringBoot</strong></a></div>\n"
                + "		<div\n" + "			style=\"text-align: center; padding: 4px\">Spring Boot html Email.</div>\n"
                + "		<img width=\"180px\" height=\"180px\"\n"
                + "			src=\"https://i0.hdslb.com/bfs/album/6d1ed1848611db806d7d40660d84fd1af0e7dfda.jpg\">\n"
                + "	</div>\n" + "</body>";
		mailManage.sendHtmlMail("sendtoqq@qq.com","test html mail",htmlContent);
		log.info("发送消息成功。");
		return ResponseResult.renderSuccess().message("发送消息成功。");
	}
	
	@RequestMapping(value="/attachmentsMailSend",method=RequestMethod.GET)
	public ResponseResult attachmentsMailSend(HttpServletRequest request) {
		String rootPath=request.getServletContext().getRealPath("/");
		String filePath = rootPath+"\\static\\AdminLTE-3.0.5\\dist\\img\\Tohsaka Rin.jpg";
		mailManage.sendAttachmentsMail("sendtoqq@qq.com","test simple mail","邮件中有附件，请注意查收！", filePath);
		log.info("发送消息成功。");
		return ResponseResult.renderSuccess().message("发送消息成功。");
	}
	
	@RequestMapping(value="/sendTemplateMail",method=RequestMethod.GET)
	public ResponseResult sendTemplateMail(){
        try {
        	Map<String, Object> context = new HashMap<String, Object>();
        	context.put("project", "gousadeadminlte");
        	context.put("author", "woxigousade");
        	context.put("url", "https://github.com/woxigousade/gousade");
            mailManage.sendTemplateMail("sendtoqq@qq.com", "这是模板邮件", context, "emailTemp");
        }catch (Exception ex){
            ex.printStackTrace();
            return ResponseResult.renderError().message("发送消息失败。");
        }
        return ResponseResult.renderSuccess().message("发送消息成功。");
    }

}
