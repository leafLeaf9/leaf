package com.gousade.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gousade.pojo.Message;

public interface MessageMapper {

	public void toMessage(@Param("userId") String id, @Param("message")String message);
	
	public List<Message> findMessage(@Param("userId") String id);
	
	public void updateMessage(@Param("messId") String messId);

}
