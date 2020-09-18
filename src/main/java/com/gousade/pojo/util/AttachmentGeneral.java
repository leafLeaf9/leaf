package com.gousade.pojo.util;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import lombok.Data;

/**
 * @author woxi-Gisard
 * @date 2020年8月11日 下午9:46:30
 */
@Data
public class AttachmentGeneral implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5456864622650142492L;

	@TableId(type = IdType.ASSIGN_ID)
	private String id;
	/**
	 * 关联id
	 */
	private String attachId;
	/**
	 * 附件名称
	 */
	private String attachName;
	/**
	 * 附件类型
	 */
	private String attachType;
	/**
	 * 附件大小
	 */
	private String attachSize;
	/**
	 * 附件路径
	 */
	private String attachPath;

	@TableField(fill = FieldFill.INSERT)
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	private boolean delflag;

}
