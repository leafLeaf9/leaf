package com.gousade.util;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;

public class ExceptionStackTraceUtil {
    public static String getExceptionStackTraceString(Throwable e) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintWriter printWriter = new PrintWriter(byteArrayOutputStream);
        e.printStackTrace(printWriter);
        printWriter.flush();
        return byteArrayOutputStream.toString();
    }
}
