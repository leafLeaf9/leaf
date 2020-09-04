package com.gousade.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * @author woxigsd@gmail.com
 * @date 2020-9-4 17:08:29
 * @description 
 */
@Slf4j
@Component
public class MailManageImpl implements MailManage{
	
	@Autowired
    private JavaMailSender javaMailSender;
	
	@Override
	public void sendSimpleMail(String to, String subject, String content) {
		SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("1207366201");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);

        try {
        	javaMailSender.send(message);
            log.info("简单邮件已经发送。");
        } catch (Exception e) {
        	log.error("发送简单邮件时发生异常！", e);
        }
	}

}
