package com.gousade.controller;

import com.gousade.annotation.RequestSentinel;
import com.gousade.utils.OpenOfficeUtil;
import com.gousade.utils.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.jodconverter.DocumentConverter;
import org.jodconverter.document.DefaultDocumentFormatRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @author woxigousade
 * @date 2019/7/22
 */
@Slf4j
@RestController
@RequestMapping(value = "/admin/gousadeTest/filePreview")
public class FilePreviewController {

    @Autowired
    private DocumentConverter converter;

    @Autowired
    private HttpServletResponse response;

    @Resource
    private OpenOfficeUtil openOfficeUtil;

    /**
     * PDF预览
     */
    @GetMapping("/previewPDF")
    public void previewPDF() {
        String path = "static" + File.separator + "pdf" + File.separator + "Java并发编程的艺术.pdf";
        String filename = "Java并发编程的艺术.pdf";
        ClassPathResource classPathResource = new ClassPathResource(path);
        response.reset();
        try (InputStream input = classPathResource.getInputStream(); OutputStream output = response.getOutputStream()) {
//            String fileName = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
//            FileInputStream input = new FileInputStream("E:\\document\\PENET协议_R_2015.pdf");
            byte[] bytes = new byte[1024];
            int length;
            while ((length = input.read(bytes)) != -1) {
                output.write(bytes, 0, length);
                output.flush();
            }
            /*response.setContentType("application/pdf");
            response.addHeader("Content-Disposition", "inline;filename*=utf-8''" + fileName);*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 文档预览，word、excel、ppt都可以转换为pdf，excel样式可能会出现问题
     */
    @GetMapping("/previewDocument")
    public void previewDocument() {
        String path = "static" + File.separator + "pdf" + File.separator + "测试word1.docx";
        String filename = "测试word.pdf";
        File convertDictionary = new File("D:/convertToPDF");
        if (!convertDictionary.exists()) {
            convertDictionary.mkdirs();
        }
        File convertedFile = new File(convertDictionary.getAbsolutePath() + File.separator + filename);
        ClassPathResource classPathResource = new ClassPathResource(path);
        response.reset();
        try (OutputStream output = response.getOutputStream()) {
            converter.convert(classPathResource.getInputStream()).as(DefaultDocumentFormatRegistry.PDF)
                    .to(convertedFile).execute();
            InputStream input = new FileInputStream(convertedFile);
            byte[] bytes = new byte[1024];
            int length;
            while ((length = input.read(bytes)) != -1) {
                output.write(bytes, 0, length);
                output.flush();
            }
            input.close();
            //需要保存文件用上面的方法，如果不需要保存文件直接预览则直接输出为PDF流，输入的as类型似乎没影响
            converter.convert(classPathResource.getInputStream()).as(DefaultDocumentFormatRegistry.PDF)
                    .to(output).as(DefaultDocumentFormatRegistry.PDF)
                    .execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 只使用local包下的类进行文档转换预览
     */
    @GetMapping("/previewDocumentByLocal")
    private void previewDocumentByLocal() {
        String path = "static" + File.separator + "pdf" + File.separator + "防汛保畅日报template.docx";
        String filename = "测试wordLocal.pdf";
        File convertDictionary = new File("D:/convertToPDFLocal");//转换之后文件生成的地址
        if (!convertDictionary.exists()) {
            convertDictionary.mkdirs();
        }
        File convertedFile = new File(convertDictionary.getAbsolutePath() + File.separator + filename);
        ClassPathResource classPathResource = new ClassPathResource(path);
        response.reset();
        try (OutputStream output = response.getOutputStream()) {
            openOfficeUtil.convert(classPathResource.getInputStream(), convertedFile);
            InputStream input = new FileInputStream(convertedFile);
            byte[] bytes = new byte[1024];
            int length;
            while ((length = input.read(bytes)) != -1) {
                output.write(bytes, 0, length);
                output.flush();
            }
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 图片预览
     */
    @RequestSentinel
    @GetMapping("/previewImage")
    public void previewImage() {
        response.reset();
        response.setContentType("text/html; charset=UTF-8");
        response.setContentType("image/jpeg");
        String path = "static" + File.separator + "sliderImages" + File.separator + "background"
                + File.separator + "background (1).jpg";
        ClassPathResource classPathResource = new ClassPathResource(path);
        try (InputStream input = classPathResource.getInputStream();
             OutputStream output = response.getOutputStream()) {
            byte[] bytes = new byte[1024];
            int length;
            while ((length = input.read(bytes)) != -1) {
                output.write(bytes, 0, length);
                output.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 文件下载
     */
    @GetMapping("/fileDownload")
    public void fileDownload(String filename) throws IOException {
       /* String filename = "食驚第一暗帝 永恒之海(灵宝：时间真理).png";
        String path = "D:" + File.separator + "gousadeFiles" + File.separator + "generalfile" + File.separator + dateDtr
                + File.separator + filename;*/
//		response.reset();
//        response.setContentType("application/force-download");
//        response.addHeader("Content-Disposition", "attachment;fileName=" + filename);
//        byte[] buffer = new byte[1024];
//        try (FileInputStream fis = new FileInputStream(file);
//        BufferedInputStream bis = new BufferedInputStream(fis)) {
//        	OutputStream os = response.getOutputStream();
//            int i = bis.read(buffer);
//            while (i != -1) {
//                os.write(buffer, 0, i);
//                i = bis.read(buffer);
//            }
//        }
        /*InputStream fis = new BufferedInputStream(new FileInputStream(path));
        byte[] buffer = new byte[fis.available()];
        fis.read(buffer);
        fis.close();
        // 清空response
        response.reset();
        // 设置response的Header
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/octet-stream;charset=UTF-8");
        response.addHeader("Content-Disposition",
                "attachment;filename=" + new String(filename.getBytes("gb2312"), "ISO8859-1"));
        response.addHeader("Content-Length", "" + file.length());

        OutputStream toClient = new BufferedOutputStream(response.getOutputStream());

        toClient.write(buffer);
        toClient.flush();
        toClient.close();*/
        String path = "static" + File.separator + filename;
        ResponseUtils.resourceFileDownload(response, path, filename.substring(filename.lastIndexOf("/") + 1));
    }
}
