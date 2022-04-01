package com.gousade.util;

import org.springframework.util.ObjectUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class NetUtils {
    /**
     * 超时时间，单位为毫秒
     */
    private static final int TIME_OUT = 200;

    /**
     * 校验是否可以连通
     *
     * @param ip connect ip
     * @return true/false
     */
    public static boolean canConnect(String ip) {
        boolean connect = false;
        Runtime runtime = Runtime.getRuntime();
        Process process;
        try {
            process = runtime.exec(String.format("ping %s -n 3 -w %d", ip, TIME_OUT));
            InputStream is = process.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            is.close();
            isr.close();
            br.close();

            if (!ObjectUtils.isEmpty(sb.toString())) {
                connect = sb.toString().indexOf("TTL") > 0;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return connect;
    }

    public static boolean isIp(String str) {
        return str.matches("^(25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[0-9]{1,2})(\\.(25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[0-9]{1,2})){3}$");
    }

    public static void main(String[] args) {
        System.out.println(isIp("127.0.0.1"));
        System.out.println(canConnect("127.0.0.1"));
    }
}
