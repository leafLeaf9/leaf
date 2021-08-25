package com.gousade.art.concurrent.book.chapter05;

import com.gousade.art.concurrent.book.chapter04.SleepUtils;
import org.junit.jupiter.api.Test;

import java.util.concurrent.locks.Lock;

/**
 * @author woxigousade
 * @date 2021/8/25
 */
public class TwinsLockTest {

    @Test
    public void test() throws InterruptedException {
        final Lock lock = new TwinsLock();
        class Worker extends Thread {
            public void run() {
                while (true) {
                    lock.lock();
                    try {
                        SleepUtils.second(1);
                        System.out.println(Thread.currentThread().getName());
                        SleepUtils.second(1);
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }
        // 启动10个线程
        for (int i = 0; i < 10; i++) {
            Worker w = new Worker();
            w.setDaemon(true);
            w.start();
        }
        // 每隔1秒换行
        for (int i = 0; i < 10; i++) {
            SleepUtils.second(1);
            System.out.println();
        }
    }
}