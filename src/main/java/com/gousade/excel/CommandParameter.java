package com.gousade.excel;

import lombok.Data;

import java.io.Serializable;

@Data
public class CommandParameter implements Serializable {
    private int index; // 参数编号 1~26
    private String inputType;
    private CommandInfo commandInfo = new CommandInfo();

    public void processParamType() {
        switch (inputType) {
            case "无符号整型":
            case "组数":
                commandInfo.setParamType("1");
                break;
            case "单精度浮点数":
            case "双精度浮点数":
                commandInfo.setParamType("0");
                break;
            default:
                commandInfo.setParamType("3");
                break;
        }
    }
}
