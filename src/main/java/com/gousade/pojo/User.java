package com.gousade.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Set;

@ApiModel(/*value = "User",*/ description = "用户实体类")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -1283526925605483104L;

    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    private String userId;

    @Size(max = 50, message = "用户名称不能超过{max}个字符")
    private String userName;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JSONField(serialize = false)
    @TableField(updateStrategy = FieldStrategy.NOT_EMPTY)
    @Schema(hidden = true) // swagger文档中隐藏此属性
    private String password;

    @JSONField(serialize = false)
    @Schema(hidden = true)
    private String salt;

    @TableField(fill = FieldFill.INSERT)
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private ZonedDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    private String remark;

    private String phoneNumber;

    private Date lastlogintime;

    private boolean delflag;

    @ApiModelProperty(value = "用户头像路径")
    private String avatarPath;

    @TableField(fill = FieldFill.INSERT)
    @Version
    private Integer version;

    @TableField(exist = false)
    @JSONField(serialize = false)
    private Set<String> roles;

    @TableField(exist = false)
    @JSONField(serialize = false)
    private Set<String> urls;

    @TableField(exist = false) // 不在数据库表中 但java逻辑中需要使用
    @JSONField(serialize = false)
    private String roleIds;

}
