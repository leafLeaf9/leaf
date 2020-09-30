package com.gousade.entity;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author woxigousade <woxigsd@gmail.com>
 * @since 2020-09-25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "EasyExcelData对象", description = "")
@ExcelIgnoreUnannotated
public class EasyExcelData implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @ApiModelProperty(value = "字符串标题")
    @ExcelProperty("字符串标题")
    private String string;

    @ApiModelProperty(value = "数字标题")
    @ExcelProperty("数字标题")
    private Double doubleData;

    @ApiModelProperty(value = "日期标题")
    @ExcelProperty("日期标题")
    @TableField(value = "date", fill = FieldFill.INSERT)
    private Date createTime;

    private boolean delflag;

}
