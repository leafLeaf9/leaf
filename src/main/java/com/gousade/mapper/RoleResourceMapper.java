package com.gousade.mapper;

import com.gousade.pojo.RoleResource;

import java.util.List;

public interface RoleResourceMapper {

    int insert(RoleResource record);

    int insertBatch(List<RoleResource> list);

    int deleteByRoleId(String roleId);
}