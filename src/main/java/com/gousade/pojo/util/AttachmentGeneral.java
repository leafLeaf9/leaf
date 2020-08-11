package com.gousade.pojo.util;

import java.io.Serializable;

import lombok.Data;

/** 
* @author 作者: woxi-Gisard
* @version 创建时间:2020年8月11日 下午9:46:30 
* 类说明:附件通用类
*/
@Data
public class AttachmentGeneral implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5456864622650142492L;
	
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

}
