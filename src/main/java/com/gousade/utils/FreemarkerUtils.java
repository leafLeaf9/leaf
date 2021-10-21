package com.gousade.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @author woxigousade
 * @date 2021/10/21
 */
@Slf4j
public class FreemarkerUtils {
    private volatile static FreemarkerUtils instance;

    private FreemarkerUtils() {
    }

    public static FreemarkerUtils getInstance() {
        if (instance == null) {
            synchronized (FreemarkerUtils.class) {
                if (instance == null) {
                    instance = new FreemarkerUtils();
                }
            }
        }
        return instance;
    }

    Configuration getFreemarkerConfig(File templateFile) throws IOException {
        Configuration config = new Configuration(Configuration.VERSION_2_3_31);
        config.setDefaultEncoding(StandardCharsets.UTF_8.name());
        //设置异常处理器, 这样的话, 即使没有属性也不会出错, 如：${list.name}...不会报错
        config.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
        config.setDirectoryForTemplateLoading(templateFile.getParentFile());
        return config;
    }

    /**
     * 渲染模板并保存
     *
     * @param data             渲染数据
     * @param templateFilePath 模板文件路径
     * @param outputFilePath   保存文件路径，文件名带特殊符号会异常，如果携带日期可使用yyyyMMddHHmmss
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void processTemplate(Object data, String templateFilePath, String outputFilePath) {
        File templateFile = new File(templateFilePath);
        try {
            Configuration config = getFreemarkerConfig(templateFile);
            Template template = config.getTemplate(templateFile.getName());
            File outputFile = new File(outputFilePath);
            outputFile.delete();
            if (!outputFile.getParentFile().exists()) {
                outputFile.getParentFile().mkdirs();
            }
            Writer out = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(outputFile), StandardCharsets.UTF_8));
            template.process(data, out);
            out.close();
            log.info("freemarker导出文件成功。" + outputFilePath);
        } catch (Exception e) {
            throw new RuntimeException("freemarker渲染模板时发生异常。", e);
        }
    }
}
