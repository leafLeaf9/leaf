package com.gousade.util;

import lombok.extern.slf4j.Slf4j;
import org.jodconverter.JodConverter;
import org.jodconverter.document.DefaultDocumentFormatRegistry;
import org.jodconverter.office.LocalOfficeManager;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.File;
import java.io.InputStream;

/**
 * @author woxigousade
 * @date 2021/7/27
 */
@Component
@Slf4j
public class OpenOfficeUtil {
    private static final int PORT = 18101;
    private LocalOfficeManager officeManager;

    @PostConstruct
    public void init() {
        try {
            officeManager = LocalOfficeManager.builder().install()
                    .portNumbers(LocalMachineUtils.getAvailablePort(PORT)).build();
            startOfficeManager();
        } catch (Exception e) {
            log.error("注册officeManager发生异常。", e);
        }
    }

    @PreDestroy
    public void destroy() {
        stopOfficeManager();
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
