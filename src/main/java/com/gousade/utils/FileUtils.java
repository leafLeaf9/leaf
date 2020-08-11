package com.gousade.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Jan 26, 2015
 * 
 *  文件工具类
 */
public class FileUtils {

    // 将MultipartFile 转换为File
    public static void SaveFileFromInputStream(InputStream stream, String path, String savefile) throws IOException {
        FileOutputStream fs = new FileOutputStream(path + "/" + savefile);
        byte[] buffer = new byte[1024 * 1024];
        int byteread = 0;
        while ((byteread = stream.read(buffer)) != -1) {
            fs.write(buffer, 0, byteread);
            fs.flush();
        }
        fs.close();
        stream.close();
    }
}
