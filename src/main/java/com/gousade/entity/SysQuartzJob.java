package com.gousade.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * <p>
 * 定时任务调度表
 * </p>
 *
 * @author woxigousade
 * @since 2022-07-21
 */
@Getter
@Setter
@TableName("t_sys_quartz_job")
@ApiModel(value = "SysQuartzJob对象", description = "定时任务调度表")
public class SysQuartzJob {

    @ApiModelProperty("日志id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty("任务名称")
    private String jobName;

    @ApiModelProperty("任务组名")
    private String jobGroup;

    @ApiModelProperty("调用目标字符串")
    private String invokeTarget;

    @ApiModelProperty("cron执行表达式")
    private String cronExpression;

    @ApiModelProperty("cron计划策略")
    private String misfirePolicy;

    @ApiModelProperty("是否并发执行（0允许 1禁止）")
    private String concurrent;

    @ApiModelProperty("任务状态（0正常 1暂停）")
    private Integer status;

    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty("逻辑删除")
    @TableLogic
    @TableField(value = "is_deleted")
    private Boolean deleted;


}
