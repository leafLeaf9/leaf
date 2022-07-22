package com.gousade.java8.socket;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class DatagramSocketTests {
    public static void main(String[] args) throws Exception {
        System.out.println(System.getProperty("catalina.home"));
        DatagramSocket socket = new DatagramSocket(666);
        byte[] sendByteArray = "Hello 你好".getBytes();
        DatagramPacket sendPacket = new DatagramPacket(
                sendByteArray,
                sendByteArray.length,
                new InetSocketAddress("localhost", 666)); //可以自己发给自己
        socket.send(sendPacket);
        byte[] bf = new byte[3000];
        DatagramPacket receivePacket = new DatagramPacket(bf, bf.length);
        socket.receive(receivePacket);
        int receiveLength = receivePacket.getLength();
        byte[] receiveByteArray = new byte[receiveLength];
        System.arraycopy(bf, 0, receiveByteArray, 0, receiveLength);
        String receiveString = new String(receiveByteArray);
        System.out.println(receiveString);
        socket.close();
    }

}
