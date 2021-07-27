package com.gousade.utils;

import lombok.extern.slf4j.Slf4j;
import org.jodconverter.JodConverter;
import org.jodconverter.office.LocalOfficeManager;
import org.jodconverter.office.OfficeException;

import java.io.File;

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
    private final LocalOfficeManager officeManager;

    private OpenOfficeUtil() {
        officeManager = LocalOfficeManager.builder().install()
                /*.officeHome(OFFICE_HOME)*/.portNumbers(PORT).build();
        //officeManager = LocalOfficeManager.install();
        startOfficeManager();
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
        try {
            officeManager.start();
        } catch (OfficeException e) {
            log.error("启动officeManager发生异常。", e);
        }
    }

    public void stopOfficeManager() {
        try {
            officeManager.stop();
        } catch (OfficeException e) {
            log.error("停止officeManager发生异常。", e);
        }
    }

    public void convert(File source, File target) {
        try {
            JodConverter.convert(source).to(target).execute();
        } catch (OfficeException e) {
            log.error("officeManager转换文档发生异常。", e);
        }
    }

    /*public static void main(String[] args) {
        File source = new File("C:\\Users\\Administrator\\Desktop\\测试word.docx");
        String filename = "测试wordLocal.pdf";
        File convertDictionary = new File("D:/convertToPDFLocalGaoGuanTong");
        if (!convertDictionary.exists()) {
            convertDictionary.mkdirs();
        }
        File target = new File(convertDictionary.getAbsolutePath() + File.separator + filename);
        OpenOfficeUtil.getInstance().convert(source, target);
    }*/

}
