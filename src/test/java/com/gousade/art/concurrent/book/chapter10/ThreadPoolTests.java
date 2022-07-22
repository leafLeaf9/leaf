package com.gousade.art.concurrent.book.chapter10;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.*;

/**
 * @author woxigousade
 * @date 2022/1/17
 */
@Slf4j
public class ThreadPoolTests {
    public static final String THREAD_NAME_PREFIX = "sub-center-network-detect-thread-";
    public static final String THREAD_NAME_PREFIX_1 = "1sub-center-network-detect-thread-";
    private static final ExecutorService THREAD_POOL = new ThreadPoolExecutor(4, 4,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(100), new CustomizableThreadFactory(THREAD_NAME_PREFIX));
    private static final ExecutorService THREAD_POOL1 = Executors
            .newCachedThreadPool(new CustomizableThreadFactory(THREAD_NAME_PREFIX_1));

    public static void main(String[] args) throws InterruptedException {
//        THREAD_POOL.execute(() -> log.info("测试"));
        for (int i = 0; i < 6; i++) {
            THREAD_POOL1.execute(() -> log.info("测试1"));
        }
        Thread.sleep(1000);
        for (int i = 0; i < 5; i++) {
            THREAD_POOL1.execute(() -> log.info("测试1"));
        }

    }

    /**
     * 测试队列已满时，新来的任务执行顺序
     * 新来的会比已在队列中的先执行
     */
    @Test
    public void testOverflowWorkQueueNewCommand() throws InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                3,
                6,
                10,
                TimeUnit.MINUTES,
                new ArrayBlockingQueue<>(3));
        for (int i = 1; i <= 9; i++) {
            int finalI = i;
            threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + " is executing" + finalI);
                    try {
                        Thread.sleep(10000000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public String toString() {
                    return "thread" + finalI;
                }
            });
        }
        Thread.sleep(3 * 1000);
        System.out.println(threadPoolExecutor.getQueue());
        Assertions.assertEquals("[thread4, thread5, thread6]", threadPoolExecutor.getQueue().toString());
    }
}
