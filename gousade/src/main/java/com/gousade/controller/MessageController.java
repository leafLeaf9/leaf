package com.gousade.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gousade.pojo.Message;
import com.gousade.service.MessageService;

@Controller
public class MessageController {
	@Autowired
	private MessageService messageService;
	
	
	@RequestMapping("/findMessageList")
	
	public String findMessageList(@RequestParam("userId")String id,Model model) {
		List<Message> messageList = null;
		try {
			 messageList = messageService.findMessage(id);
			model.addAttribute("messageList", messageList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "forward:/message_new";
	}
	
	@RequestMapping("/showMessage")
	public String updateMessage(@RequestParam("messId") String messId,
		@RequestParam("message") String message,Model model) {
		try {
			messageService.updateMessage(messId);
			model.addAttribute("message", message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "forward:/message_show";
	}
	
	
	
}
