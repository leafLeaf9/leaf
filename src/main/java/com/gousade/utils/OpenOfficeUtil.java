package com.gousade.utils;

import lombok.extern.slf4j.Slf4j;
import org.jodconverter.JodConverter;
import org.jodconverter.document.DefaultDocumentFormatRegistry;
import org.jodconverter.office.LocalOfficeManager;

import java.io.File;
import java.io.InputStream;

/**
 * 文档转化工具类
 *
 * @author woxigousade
 * @date 2021/7/27
 */
@Slf4j
public class OpenOfficeUtil {
    private static final String OFFICE_HOME = "C:/Program Files (x86)/OpenOffice 4";
    private static final int PORT = 18101;
    private static volatile OpenOfficeUtil instance;
    private LocalOfficeManager officeManager;

    private OpenOfficeUtil() {
        try {
            officeManager = LocalOfficeManager.builder().install()
                    /*.officeHome(OFFICE_HOME)*/.portNumbers(PORT).build();
            //officeManager = LocalOfficeManager.install();
            startOfficeManager();
        } catch (Exception e) {
            log.error("注册officeManager发生异常。", e);
        }

    }

    public static OpenOfficeUtil getInstance() {
        if (instance == null) {
            synchronized (OpenOfficeUtil.class) {
                if (instance == null)
                    instance = new OpenOfficeUtil();
            }
        }
        return instance;
    }

    public void startOfficeManager() {
        if (officeManager == null) {
            log.error("officeManager未注册成功，无法启动。");
            return;
        }
        try {
            officeManager.start();
        } catch (Exception e) {
            log.error("启动officeManager发生异常。", e);
        }
    }

    public void stopOfficeManager() {
        if (officeManager == null) {
            log.error("officeManager未注册成功，无法启动。");
            return;
        }
        try {
            officeManager.stop();
        } catch (Exception e) {
            log.error("停止officeManager发生异常。", e);
        }
    }

    public void convert(File source, File target) {
        try {
            JodConverter.convert(source).to(target).execute();
        } catch (Exception e) {
            log.error("officeManager转换文档发生异常。", e);
        }
    }

    public void convert(InputStream input, File target) {
        try {
            JodConverter.convert(input).as(DefaultDocumentFormatRegistry.PDF).to(target).execute();
        } catch (Exception e) {
            log.error("officeManager转换文档发生异常。", e);
        }
    }

    /*public static void main(String[] args) {
        File source = new File("D:\\reportFilePath\\测试word.docx");
        String filename = "测试wordLocal.pdf";
        File convertDictionary = new File("D:/reportFilePath/convertToPDFLocal");
        if (!convertDictionary.exists()) {
            convertDictionary.mkdirs();
        }
        File target = new File(convertDictionary.getAbsolutePath() + File.separator + filename);
        OpenOfficeUtil.getInstance().convert(source, target);
    }*/

}
