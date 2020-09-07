package com.gousade.mail;

import java.util.Map;

/**
 * @author woxigsd@gmail.com
 * @date 2020-9-4 17:07:46
 * @description 
 */
public interface MailManage {
	
	void sendSimpleMail(String to, String subject, String content);
	
	void sendHtmlMail(String to, String subject, String content);

    void sendAttachmentsMail(String to, String subject, String content, String filePath);

    void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId);
    
    void sendTemplateMail(String to, String subject, Map<String, Object> paramMap, String template);

}
