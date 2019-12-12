package com.gousade.mapper;

import java.util.List;

import com.gousade.pojo.Resource;

public interface ResourceMapper {
	
	List<Resource> selectResourceList();
	
    int deleteByPrimaryKey(String id);

    int insert(Resource record);

    int insertSelective(Resource record);

    Resource selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Resource record);

    int updateByPrimaryKey(Resource record);
}