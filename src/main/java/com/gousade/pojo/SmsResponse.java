package com.gousade.pojo;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
* @author woxigsd@gmail.com
* @date 2020-8-31 9:09:00
* @description sms_response entity
*/
@Data
@ApiModel(description="短信日志实体类")
public class SmsResponse implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -281509376203163622L;
	
	@TableId(type = IdType.ASSIGN_ID)
	private String id;
	
	private String response;
	
	@TableField(fill = FieldFill.INSERT)
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
	
	@TableLogic
	@ApiModelProperty(value = "逻辑删除 1(true)已删除， 0(false)未删除")
    private boolean delflag;

}
