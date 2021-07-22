package com.gousade.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * @author woxigousade
 * @date 2019/7/22
 */
@Slf4j
@RestController
@RequestMapping(value = "/admin/gousadeTest/filePreview")
public class FilePreviewController {

    /**
     * PDF预览
     */
    @GetMapping("/previewPDF")
    public void previewPDF(HttpServletResponse response) {
        String path = "classpath:static" + File.separator + "pdf" + File.separator + "Java并发编程的艺术.pdf";
        String filename = "Java并发编程的艺术.pdf";
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource(path);
        response.reset();
        try (InputStream input = resource.getInputStream(); OutputStream output = response.getOutputStream()) {
//            String fileName = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
//            FileInputStream input = new FileInputStream("E:\\document\\PENET协议_R_2015.pdf");
            byte[] buffer = new byte[input.available()];
            input.read(buffer);
            output.write(buffer);
            output.flush();
            /*response.setContentType("application/pdf");
            response.addHeader("Content-Disposition", "inline;filename*=utf-8''" + fileName);*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 图片预览
     */
    @GetMapping("/previewImage")
    public void picPreview(HttpServletResponse response) throws IOException {
        response.reset();
        response.setContentType("text/html; charset=UTF-8");
        response.setContentType("image/jpeg");
        String path = "static" + File.separator + "sliderImages" + File.separator + "background"
                + File.separator + "background (1).jpg";
        ClassPathResource classPathResource = new ClassPathResource(path);
        try (InputStream input = classPathResource.getInputStream();
             OutputStream output = response.getOutputStream()) {
            byte[] buffer = new byte[input.available()];
            input.read(buffer);
            output.write(buffer);
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 文件下载
     */
    @GetMapping("/fileDownload")
    public void fileDownload(HttpServletResponse response) throws IOException {
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
        String path = "classpath:static" + File.separator + "pdf" + File.separator + "Java并发编程的艺术.pdf";
        String filename = "Java并发编程的艺术.pdf";
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource(path);
        String fileName = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");

        response.reset();
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType("application/octet-stream");
        response.addHeader("Content-Disposition", "attachment;filename*=utf-8''" + fileName);
//        FileInputStream input = new FileInputStream("E:\\document\\wordDemo.docx");
        InputStream input = resource.getInputStream();
        byte[] buffer = new byte[input.available()];
        input.read(buffer);
        input.close();
        OutputStream out = new BufferedOutputStream(response.getOutputStream());
        out.write(buffer);
        out.flush();
        out.close();
    }
}
