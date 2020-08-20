package com.gousade.mapper;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.cache.annotation.Cacheable;

import com.gousade.pojo.Resource;
import com.gousade.pojo.Role;

public interface RoleMapper {
	
	List<Role> selectRoleList(Map<String,Object> map);
	
    int deleteByPrimaryKey(String id);

    int insert(Role record);

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