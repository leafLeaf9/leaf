package com.gousade.java8;

import java.util.ArrayList;
import java.util.List;

/**
 * @author woxigousade
 * @date 2021/7/1
 */
public class StringBuilderTest {

    public static void main(String[] args) {
        List<Thread> ts = new ArrayList<Thread>(600);
        StringBuilder stringBuilder = new StringBuilder();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < 10000; i++) {
            Thread t = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    stringBuilder.append("a");
                    stringBuffer.append("a");
                }
            });
            ts.add(t);
        }

        for (Thread t : ts) {
            t.start();
        }
        // 等待所有线程执行完成
        for (Thread t : ts) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        System.out.println(stringBuilder.length());
        System.out.println(stringBuffer.length());
    }
}
