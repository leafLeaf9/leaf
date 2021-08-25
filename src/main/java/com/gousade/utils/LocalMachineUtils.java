package com.gousade.utils;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * @author woxigousade
 * @date 2021/8/24
 */
public class LocalMachineUtils {
    public static synchronized int getAvailablePort(int port) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.close();
            return port;
        } catch (IOException e) {
            return getAvailablePort(port + 1);
        }
    }
}
