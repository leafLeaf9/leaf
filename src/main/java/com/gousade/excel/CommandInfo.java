package com.gousade.excel;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ExcelIgnoreUnannotated
@Data
public class CommandInfo implements Serializable {
    @ExcelProperty("PID")
    private String pid;

    @ExcelProperty("PCAT")
    private String pcat;

    @ExcelProperty("指令代号")
    @Schema(description = "指令代号")
    private String code;

    @Schema(description = "分系统")
    private String subsystem;

    @ExcelProperty("指令名称")
    @Schema(description = "指令名称")
    private String packetName;

    @ExcelProperty("默认值")
    @Schema(description = "参数默认值 参数内容 指令码属于默认参数 相当于参数0")
    private String command;

    @ExcelProperty("参数代号")
    @Schema(description = "参数代号 目前都是空")
    private String paramCode;

    @Schema(description = "参数形式 无参 变参")
    private String paramForm;

    @ExcelProperty("参数名称")
    @Schema(description = "参数名称")
    private String paramName;

    @ExcelProperty("参数长度(bit)")
    @Schema(description = "参数长度(bit位长度)")
    private String paramLength;

    @ExcelProperty("参数类型")
    @Schema(description = "参数类型 public enum DataType {DECIMAL, INT, BIT, HEX, PKGCNT, PKGLEN, CHECK, TEXT, TIME}")
    private String paramType;

    @ExcelProperty("字典")
    @Schema(description = "字典")
    private String dictionary;

    private List<CommandParameter> parameterList = new ArrayList<>();
}
