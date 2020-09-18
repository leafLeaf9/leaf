package com.gousade.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gousade.pojo.User;
import com.gousade.pojo.util.AttachmentGeneral;

public interface UserMapper extends BaseMapper<User> {

	int deleteByPrimaryKey(String id);

	User selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(User record);

	int updateByPrimaryKey(User record);

	public User SelectUserByLoginName(String userId);

	public int regist(Map<String, Object> map);

	public List<Map<String, Object>> queryuserlist(Map<String, Object> paraMap);

//	@Cacheable(value="redis@Cacheable")
	public List<User> selectUserList(Map<String, Object> searchMap);

	public long queryuserlistcnt(Map<String, Object> map);

	/**
	 * 更新登录时间
	 */
	public int updateLoginTime(String id);

	public int updateUserById(User entity);

	int deleteUserByid(Map<String, Object> map);

	int uploadUserAvatar(AttachmentGeneral attachmentGeneral);

	int updateOwnPasswordById(User user);

}
