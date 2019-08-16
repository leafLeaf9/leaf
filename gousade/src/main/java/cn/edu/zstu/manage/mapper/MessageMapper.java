package cn.edu.zstu.manage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.edu.zstu.manage.pojo.Message;

public interface MessageMapper {

	public void toMessage(@Param("userId") String id, @Param("message")String message);
	
	public List<Message> findMessage(@Param("userId") String id);
	
	public void updateMessage(@Param("messId") String messId);

}
