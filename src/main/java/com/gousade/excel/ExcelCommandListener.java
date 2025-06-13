package com.gousade.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.gousade.util.ChineseUtil;
import org.springframework.util.ObjectUtils;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ExcelCommandListener extends AnalysisEventListener<Map<Integer, String>> {

    private List<CommandInfo> commandList = new ArrayList<>();
    private Map<Integer, String> headMap;

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        this.headMap = headMap; // 保存表头（列索引 -> 表头名）
    }

    @Override
    public void invoke(Map<Integer, String> data, AnalysisContext context) {
        CommandInfo info = new CommandInfo();
        info.setParamType("3");
        info.setParamName("指令码");
        List<CommandParameter> paramList = new ArrayList<>();
        for (Map.Entry<Integer, String> entry : headMap.entrySet()) {
            Integer colIndex = entry.getKey();
            String header = entry.getValue();
            String value = data.get(colIndex);

            if (value == null || value.trim().isEmpty()) {
                continue;
            }

            // 固定字段处理
            switch (header) {
                case "代号":
                    info.setCode(value);
                    break;
                case "分系统":
                    info.setSubsystem(value);
                    break;
                case "遥控包名称":
                    info.setPacketName(value);
                    break;
                case "PID(b)":
                    info.setPid(processPidTo5Bit(value));
                    break;
                case "PCAT(b)":
                    info.setPcat(processPcatTo5Bit(value));
                    break;
                case "指令码(H)":
                    info.setCommand(value);
                    info.setParamLength(String.valueOf(value.replace("0X", "")
                            .replace("0x", "").length() * 4));
                    break;
                case "参数形式":
                    info.setParamForm(value);
                    break;
                default:
                    // 参数字段统一处理
                    if (header.startsWith("参数")) {
                        Pattern pattern = Pattern.compile("参数(\\d+)(.+)");
                        Matcher matcher = pattern.matcher(header);
                        if (matcher.matches()) {
                            int index = Integer.parseInt(matcher.group(1));
                            String field = matcher.group(2);

                            // 查找对应参数对象（或新建）
                            CommandParameter param = null;
                            for (CommandParameter p : paramList) {
                                if (p.getIndex() == index) {
                                    param = p;
                                    break;
                                }
                            }
                            if (param == null) {
                                param = new CommandParameter();
                                param.setIndex(index);
                                paramList.add(param);
                            }

                            // 设置字段值
                            switch (field) {
                                case "名称":
                                    param.getCommandInfo().setParamName(value);
                                    break;
                                case "输入类型":
                                    param.setInputType(value);
                                    break;
                                case "长度(b)":
                                    param.getCommandInfo().setParamLength(value);
                                    break;
                                case "内容":
                                    // 这里有些内容是无用的中文 需要结合参数类型做处理 类型
                                    if (ChineseUtil.containsChinese(value)) {
                                        break;
                                    }
                                    param.getCommandInfo().setCommand(value);
                                    break;

                                case "物理意义列表":
                                    param.getCommandInfo().setDictionary(value.replace(",", ":"));
                                    break;
                                default:
                                    break;
                            }
                        }
                    }
                    break;
            }
        }

        List<CommandParameter> nonEmptyParams = new ArrayList<>();
        for (CommandParameter p : paramList) {
            if (!ObjectUtils.isEmpty(p.getCommandInfo().getParamName())) {
                nonEmptyParams.add(p);
            }
        }
        info.setParameterList(nonEmptyParams);

        commandList.add(info);
    }

    private String processPcatTo5Bit(String value) {
        if ("直接指令".equals(value)) {
            return "00000";
        }
        Integer i = Integer.valueOf(value.replace("任务", ""));
        String binaryString = Integer.toString(i, 2);
        return String.format("%" + 5 + "s", binaryString).replace(' ', '0');
    }


    private String processPidTo5Bit(String value) {
        if ("直接指令".equals(value)) {
            return "00000";
        }
        Integer i = pidMap.get(value);
        if (i != null) {
            String binaryString = Integer.toString(i, 2);
            return String.format("%" + 5 + "s", binaryString).replace(' ', '0');
        }
        return value;
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        System.out.println("所有数据解析完成！");
        commandList.forEach(e -> {
            e.getParameterList().forEach(p -> {
                p.getCommandInfo().setCode(e.getCode());
                p.getCommandInfo().setSubsystem(e.getSubsystem());
                p.getCommandInfo().setPacketName(e.getPacketName());
                p.getCommandInfo().setPid(e.getPid());
                p.getCommandInfo().setPcat(e.getPcat());
                p.processParamType();
            });
        });
        List<CommandInfo> result = new ArrayList<>();
        commandList.forEach(e -> {
            // 变参的不导出
            if ("变参".equals(e.getParamForm())) {
                return;
            }
            result.add(e);
            result.addAll(e.getParameterList().stream().map(CommandParameter::getCommandInfo).collect(Collectors.toList()));
        });
        result.forEach(e -> {
            if ("直接指令".equals(e.getSubsystem())) {
                e.setCommand(e.getCommand() + e.getCommand() + e.getCommand());
                e.setParamLength(String.valueOf(Integer.parseInt(e.getParamLength()) * 3));
            }
        });
        System.out.println("准备导出数据！");
        Map<String, List<CommandInfo>> collect = result.stream().collect(Collectors.groupingBy(CommandInfo::getSubsystem,
                LinkedHashMap::new, Collectors.toList()));
        String pathName = "C:\\Users\\Administrator\\Desktop\\指令导出" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH mm ss")) + ".xlsx";
        try (ExcelWriter excelWriter = EasyExcel.write(pathName, CommandInfo.class).build()) {
            int sheetNo = 0;
            for (Map.Entry<String, List<CommandInfo>> entry : collect.entrySet()) {
                String sheetName = entry.getKey();
                List<CommandInfo> sheetData = entry.getValue();
                WriteSheet writeSheet = EasyExcel.writerSheet(sheetNo++, sheetName).build();
                excelWriter.write(sheetData, writeSheet);
            }
            excelWriter.finish();
        }
    }

    public static void main(String[] args) {
        String str = "值1:值1(中文描述);值2:值(中文描述);";
        String[] split = str.split(";");
        String filePath = "E:\\Documents\\【TC】ZX01_TC48.xlsx";
        ExcelCommandListener listener = new ExcelCommandListener();
        EasyExcel.read(new File(filePath), listener).sheet().doRead();
    }

    private Map<String, Integer> pidMap = new HashMap<>() {{
        put("模块零", 0);
        put("模块一", 1);
        put("模块二", 2);
        put("模块三", 3);
        put("模块四", 4);
        put("模块五", 5);
        put("模块六", 6);
        put("模块七", 7);
        put("模块八", 8);
        put("模块九", 9);
        put("模块十", 10);
        put("模块十一", 11);
        put("模块十二", 12);
        put("模块十三", 13);
        put("模块十四", 14);
        put("模块十五", 15);
        put("模块十六", 16);
        put("模块十七", 17);
        put("模块十八", 18);
        put("模块十九", 19);
        put("模块二十", 20);
        put("模块二十一", 21);
        put("模块二十二", 22);
        put("模块二十三", 23);
        put("模块二十四", 24);
        put("模块二十五", 25);
        put("模块二十六", 26);
        put("模块二十七", 27);
        put("模块二十八", 28);
        put("模块二十九", 29);
        put("模块三十", 30);
        put("模块三十一", 31);
    }};
}
