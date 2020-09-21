package com.gousade.pojo;

import lombok.Data;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

@Data
public class UserRole {

    private String id;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 角色id
     */
    private String roleId;

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
