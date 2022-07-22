package com.gousade.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 * 定时任务调度日志表
 * </p>
 *
 * @author woxigousade
 * @since 2022-07-22
 */
@Data
@TableName("t_sys_quartz_job_log")
@ApiModel(value = "SysQuartzJobLog对象", description = "定时任务调度日志表")
public class SysQuartzJobLog {

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty("任务名称")
    private String jobName;

    @ApiModelProperty("任务组名")
    private String jobGroup;

    @ApiModelProperty("调用目标字符串")
    private String invokeTarget;

    @ApiModelProperty("日志信息")
    private String jobMessage;

    @ApiModelProperty("执行状态（0正常 1失败）")
    private Integer status;

    @ApiModelProperty("异常信息")
    private String exceptionInfo;

    @ApiModelProperty("开始时间")
    private LocalDateTime startTime;

    @ApiModelProperty("结束时间")
    private LocalDateTime endTime;

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
