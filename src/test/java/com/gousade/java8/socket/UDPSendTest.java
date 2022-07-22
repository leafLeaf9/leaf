package com.gousade.java8.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.Arrays;

public class UDPSendTest {
    public static void main(String[] args) throws IOException {
        // 创建发送端Socket对象
        DatagramSocket ds = new DatagramSocket(new InetSocketAddress("192.168.12.63", 9998));
        // 自己封装键盘录入数据
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = br.readLine()) != null) {
            if (line.equals("ending")) {
                break;
            }
            // 数据打包
            byte[] b = line.getBytes();
            System.out.println("数据" + line + "转换后的字节数组为: " + Arrays.toString(b));
            DatagramPacket dp = new DatagramPacket(b, b.length, new InetSocketAddress("192.168.12.63", 10086));
            // 调用DatagramSocket对象方法发送数据
            ds.send(dp);
        }
        // 释放资源
        ds.close();

    }
}
