package com.gousade.util;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.URL;

/**
 * @author woxigousade
 * @date 2020/6/15
 */
@Slf4j
public class FileUtils {

    /**
     * 递归删除文件及文件夹
     *
     * @param file            file needed to be deleted
     * @param deletedFileName name of deleted file
     */
    public static void deleteFilesRecursively(File file, String deletedFileName) {
        if (!file.exists()) {
            return;
        }
        File[] fileArray = file.listFiles();
        if (fileArray != null) {
            for (File subFile : fileArray) {
                if (file.isDirectory()) {
                    deleteFilesRecursively(subFile, deletedFileName);
                } else {
                    boolean isDeleted = subFile.delete();
                    if (isDeleted) {
                        log.info("删除文件->{}, 路径为: {}", deletedFileName, subFile.getAbsolutePath());
                    }
                }
            }
        }
        boolean isDeleted = file.delete();
        if (isDeleted) {
            log.info("删除文件->{}, 路径为: {}", deletedFileName, file.getAbsolutePath());
        }
    }

    /**
     * @param url url
     * @return file from url
     * @throws IOException if an I/O error occurs.
     */
    public static File getFileByUrl(String url) throws IOException {
        File file;
        URL urlFile;
        InputStream inStream = null;
        OutputStream os = null;
        try {
            file = File.createTempFile("url", ".png");
            urlFile = new URL(url);
            inStream = urlFile.openStream();
            os = new FileOutputStream(file);
            int bytesRead;
            byte[] buffer = new byte[8192];
            while ((bytesRead = inStream.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
        } finally {
            if (null != os) {
                os.close();
            }
            if (null != inStream) {
                inStream.close();
            }
        }
        return file;
    }
}
