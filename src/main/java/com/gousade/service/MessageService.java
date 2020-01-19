package com.gousade.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gousade.mapper.MessageMapper;
import com.gousade.pojo.Message;

@Service
public class MessageService {
	@Autowired
	private MessageMapper messageMapper;

	public void toMessage(String id, String message) {
		messageMapper.toMessage(id,message);
	}
	
	public List<Message> findMessage(String id) {
		return messageMapper.findMessage(id);
	}
	
	public void updateMessage(String messId ) {
		messageMapper.updateMessage(messId);
	}
	
	
	
}
