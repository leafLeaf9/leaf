package com.gousade.util;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.URL;
import java.text.DecimalFormat;

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

    public static void saveFileFromInputStream(InputStream stream, String path, String fileName) throws IOException {
        FileOutputStream fs = new FileOutputStream(path + File.separator + fileName);
        byte[] buffer = new byte[1024 * 1024];
        int byteread;
        while ((byteread = stream.read(buffer)) != -1) {
            fs.write(buffer, 0, byteread);
            fs.flush();
        }
        fs.close();
        stream.close();
    }

    public static byte[] readFile(String fileName, String directory) {
        String fileWholePath;
        if (directory == null) {
            fileWholePath = fileName;
        } else {
            fileWholePath = directory + File.separator + fileName;
        }
        return readFile(fileWholePath);
    }

    public static byte[] readFile(String filePath) {
        byte[] data;
        File file = new File(filePath);
        try (FileInputStream input = new FileInputStream(file);
             ByteArrayOutputStream output = new ByteArrayOutputStream()) {
            byte[] bytes = new byte[1024];
            int length;
            while ((length = input.read(bytes)) != -1) {
                output.write(bytes, 0, length);
                output.flush();
            }
            data = output.toByteArray();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("没有找到此文件：" + filePath);
        } catch (IOException e) {
            throw new RuntimeException("读取文件流失败：FileUtil.read()");
        }
        return data;
    }

    public static byte[] readFile(InputStream input) {
        byte[] data;
        try (ByteArrayOutputStream output = new ByteArrayOutputStream()) {
            byte[] bytes = new byte[1024];
            int length;
            while ((length = input.read(bytes)) != -1) {
                output.write(bytes, 0, length);
                output.flush();
            }
            data = output.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException("读取文件流失败。");
        }
        return data;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static byte[] write2File(byte[] data, String directory, String fileName) {
        String filePath = directory + File.separator + fileName;
        File pf = new File(directory);
        if (!pf.exists()) {
            pf.mkdirs();
        }
        File file = new File(filePath);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(data);
            fos.flush();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("没有找到此文件：" + filePath);
        } catch (IOException e) {
            throw new RuntimeException("读取文件流失败：FileUtil.read()");
        }
        return data;
    }

    public static String formatFileSize(long fileSize) {
        DecimalFormat df = new DecimalFormat("#.00");
        String result;
        if (fileSize < 1024) {
            result = df.format((double) fileSize) + "B";
        } else if (fileSize < 1048576) {
            result = df.format((double) fileSize / 1024) + "KB";
        } else if (fileSize < 1073741824) {
            result = df.format((double) fileSize / 1048576) + "MB";
        } else {
            result = df.format((double) fileSize / 1073741824) + "GB";
        }
        return result;
    }
}
