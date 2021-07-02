package com.gousade.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Role implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -5058643336069716626L;

    //	@TableId(type = IdType.ASSIGN_ID)
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    private String name;

    private String remarks;

    private Integer seq;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @TableField(fill = FieldFill.INSERT)
    private boolean delflag;

}