package com.gousade.utils;

import java.io.*;
import java.net.URL;

/**
 * @author woxigousade
 * @date 2020/6/15
 */
public class FileUtil {

    public static File getFileByUrl(String url) throws IOException {
        File file;
        URL urlFile;
        InputStream inStream = null;
        OutputStream os = null;
        try {
            file = File.createTempFile("url", ".png");
            //下载
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
