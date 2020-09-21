package com.gousade.mapper;

import com.gousade.pojo.Resource;

import java.io.Serializable;
import java.util.List;

public interface ResourceMapper {
    int deleteByPrimaryKey(String id);

    int insert(Resource record);

    int insertSelective(Resource record);

    Resource selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Resource record);

    int updateByPrimaryKey(Resource record);

    List<Resource> selectResourceList();

    int deleteBatchIds(List<? extends Serializable> idList);
}