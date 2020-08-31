package com.gousade.pojo;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
* @author woxigsd@gmail.com
* @date 2020-8-31 14:24:04
* @description 
*/
@Data
@ApiModel(description="接口调用日志实体类")
public class OperationRecordLog implements Serializable {

	private static final long serialVersionUID = 3960814623848073582L;
	
	@TableId(type = IdType.ASSIGN_ID)
	private String id;
	
	private String operationPerson;
	
	private int operationNum;
	
	private String operationDescription;
	
	private String operationInterface;
	
	private String operationParam;
	
	@TableField(fill = FieldFill.INSERT)
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
	
    private boolean delflag;

}
