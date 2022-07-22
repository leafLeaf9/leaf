package com.gousade.java8.socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPReceiveTest {
    public static void main(String[] args) throws IOException {

        // 创建Socket对象接受数据，需指定端口
        DatagramSocket ds = new DatagramSocket(10086);
        while (true) {// 发送端一直发送，故用循环接收
            // 创建数据包，接收数据
            byte[] b = new byte[102400];
            DatagramPacket dp = new DatagramPacket(b, b.length);
            // 调用DatagramSocket对象方法接收数据
            ds.receive(dp);
            // 解析数据
            System.out.println(dp.getAddress() + ":" + dp.getPort());
            System.out.println(new String(dp.getData(), 0, dp.getLength()));
        }
        // 关闭接收端
        // ds.close();
    }
}
