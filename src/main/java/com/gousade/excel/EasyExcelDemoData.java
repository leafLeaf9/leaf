package com.gousade.excel;

import java.util.Date;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;

import lombok.Data;

/**
 * @author woxigsd@gmail.com
 * @date 2020-9-25 10:29:33
 * @description 
 */
@Data
public class EasyExcelDemoData {
	
	@ExcelProperty("字符串标题")
    private String string;
    @ExcelProperty("日期标题")
    private Date date;
    @ExcelProperty("数字标题")
    private Double doubleData;
    @ExcelIgnore
    private boolean delflag;

}
