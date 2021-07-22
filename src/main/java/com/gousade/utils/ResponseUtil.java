package com.gousade.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gousade.commonutils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
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
     * 下载resource目录下的文件
     */
    public static void resourceFileDownload(HttpServletResponse response, String path) {
        try {
            /*InputStream inputStream1 = Thread.currentThread().getContextClassLoader().getResourceAsStream("word.txt");
            ClassPathResource classPathResource = new ClassPathResource("word.txt");
            File sourceFile =  classPathResource.getFile();
            InputStream inputStream2 =classPathResource.getInputStream();
            File file = ResourceUtils.getFile("classpath:static/word.txt");*/
            ResourceLoader resourceLoader = new DefaultResourceLoader();
            Resource resource = resourceLoader.getResource(path);
            log.info(resource.toString());
            InputStream inputStream = resource.getInputStream();
            //输出文件
            InputStream fis = new BufferedInputStream(inputStream);
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            response.reset();
            String fileName = URLEncoder.encode(Objects.requireNonNull(resource.getFilename()), "UTF-8")
                    .replaceAll("\\+", "%20");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/octet-stream");
            response.addHeader("Content-Disposition",
                    "attachment;filename*=utf-8''" + fileName);
            OutputStream out = new BufferedOutputStream(response.getOutputStream());
            out.write(buffer);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
