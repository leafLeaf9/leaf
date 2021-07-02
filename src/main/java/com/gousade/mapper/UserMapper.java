package com.gousade.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gousade.pojo.User;
import com.gousade.pojo.util.AttachmentGeneral;

import java.util.List;
import java.util.Map;

public interface UserMapper extends BaseMapper<User> {

    int deleteByPrimaryKey(String id);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User SelectUserByLoginName(String userId);

    int regist(Map<String, Object> map);

    List<Map<String, Object>> queryuserlist(Map<String, Object> paraMap);

    //	@Cacheable(value="redis@Cacheable")
    List<User> selectUserList(Map<String, Object> searchMap);

    long queryuserlistcnt(Map<String, Object> map);

    /**
     * 更新登录时间
     */
    int updateLoginTime(String id);

    int updateUserById(User entity);

    int deleteUserByid(Map<String, Object> map);

    int uploadUserAvatar(AttachmentGeneral attachmentGeneral);

    int updateOwnPasswordById(User user);

}
