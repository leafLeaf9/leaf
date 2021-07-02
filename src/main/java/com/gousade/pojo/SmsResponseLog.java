package com.gousade.pojo;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author woxigsd@gmail.com
 * @date 2020-8-31 9:09:00
 * @description sms_response entity
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "短信日志实体类")
public class SmsResponseLog implements Serializable, TestInterface {
    /**
     *
     */
    private static final long serialVersionUID = -281509376203163622L;

    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    private String response;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableLogic
    @ApiModelProperty(value = "逻辑删除 1(true)已删除， 0(false)未删除")
    private boolean delflag;

}
