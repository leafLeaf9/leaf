package com.gousade.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gousade.commonutils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

@Slf4j
public class ResponseUtil {

    public static void out(HttpServletResponse response, ResponseResult r) {
        ObjectMapper mapper = new ObjectMapper();
        response.setStatus(HttpStatus.OK.value());
        response.setContentType("application/json;charset=UTF-8");
        try {
            mapper.writeValue(response.getWriter(), r);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 下载resource目录下的文件，直接读取流的方法在linux jar包环境下会读取不全，需要循环读取并输出流
     *
     * @param response response
     * @param path     文件路径，从resources目录开始,不需要写classpath:,如static/word.txt
     * @param filename 输出的文件名
     */
    public static void resourceFileDownload(HttpServletResponse response, String path, String filename) {
        ClassPathResource classPathResource = new ClassPathResource(path);
        try (InputStream input = classPathResource.getInputStream(); OutputStream output = response.getOutputStream()) {
            /*InputStream inputStream1 = Thread.currentThread().getContextClassLoader().getResourceAsStream("static/word.txt");
            InputStream inputStream2 =classPathResource.getInputStream();
            ClassPathResource classPathResource = new ClassPathResource("static/word.txt");
            //jar包不能getFile，只能通过流的形式读取
            File sourceFile =  classPathResource.getFile();
            File file = ResourceUtils.getFile("classpath:static/word.txt");
            ResourceLoader resourceLoader = new DefaultResourceLoader();
            Resource resource = resourceLoader.getResource(path);
            InputStream inputStream = resource.getInputStream();*/
            response.reset();
            String fileName = URLEncoder.encode(Objects.requireNonNull(filename), StandardCharsets.UTF_8.name())
                    .replaceAll("\\+", "%20");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/octet-stream");
            response.addHeader("Content-Disposition",
                    "attachment;filename*=utf-8''" + fileName);
            byte[] bytes = new byte[1024];
            int length;
            while ((length = input.read(bytes)) != -1) {
                output.write(bytes, 0, length);
                output.flush();
            }
        } catch (Exception e) {
            log.error("下载文件时发生异常。", e);
        }
    }
}
