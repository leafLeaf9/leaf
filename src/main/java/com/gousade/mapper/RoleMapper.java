package com.gousade.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gousade.pojo.Resource;
import com.gousade.pojo.Role;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface RoleMapper extends BaseMapper<Role> {

    List<Role> selectRoleList(Map<String, Object> map);

    int deleteByPrimaryKey(String id);

//    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    int deleteBatchIds(List<? extends Serializable> idList);

    List<String> selectResourceIdListByRoleId(String id);

    List<Role> getRoles();

    List<Role> findByIds(List<String> roleIdList);

    List<Resource> findAllResourcesByRoleIds(List<String> roleIdList);

    List<Resource> findResourcesByRoleIds(List<String> roleIdList);
}