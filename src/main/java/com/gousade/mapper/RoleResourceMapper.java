package com.gousade.mapper;

import java.util.List;

import com.gousade.pojo.RoleResource;

public interface RoleResourceMapper {

	int insert(RoleResource record);

	int insertBatch(List<RoleResource> list);

	int deleteByRoleId(String roleId);
}