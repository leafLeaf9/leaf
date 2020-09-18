package com.gousade.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author woxigousade<woxigsd@gmail.com>
 * @since 2020-09-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "SecretJasypt对象", description = "")
//@Schema(description="SecretJasypt对象")
public class SecretJasypt implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "id")
	@TableId(value = "id", type = IdType.ASSIGN_UUID)
	private String id;

	@ApiModelProperty(value = "混淆盐值")
	private String jasypt;

	@ApiModelProperty(value = "启动参数")
	private String vmOptions;

	@ApiModelProperty(value = "创建时间")
	private Date createTime;

	@ApiModelProperty(value = "逻辑删除")
	private Boolean delflag;

}
