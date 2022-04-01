package com.gousade.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@ApiModel(description = "路径规划实体类")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("resource_route")
public class ResourceRouteDO implements Serializable {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty(value = "起点id(可以是任何构造物、资源)")
    private String beginPointId;

    @ApiModelProperty(value = "终点id(可以是任何构造物、资源)")
    private String endPointId;

    @ApiModelProperty(value = "起点到终点的距离，单位为km")
    private Double distance;

    @ApiModelProperty(value = "路径是否启用")
    private Boolean inUse;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "逻辑删除")
    @TableField(value = "is_deleted")
    private Boolean deleted;
}
