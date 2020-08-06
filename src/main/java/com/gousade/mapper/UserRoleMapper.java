package com.gousade.mapper;

import com.gousade.pojo.UserRole;

import java.util.List;

public interface UserRoleMapper {

    int insert(UserRole userRole);

    int deleteByUserId(String id);

    List<String> findRoleIdsByUserId(String id);
}