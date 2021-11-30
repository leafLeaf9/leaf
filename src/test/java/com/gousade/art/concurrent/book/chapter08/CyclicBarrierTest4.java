package com.gousade.art.concurrent.book.chapter08;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * {@link CyclicBarrier#await(long, TimeUnit)}方法在等待超时的时候，会breakBarrier();然后抛出TimeoutException，
 * breakBarrier操作会唤醒其他在等待这个CyclicBarrier的线程trip.signalAll();并设置CyclicBarrier的broken为true，
 * 在这之后的await操作会直接抛出BrokenBarrierException，
 * 应用时要注意await(long, TimeUnit)的使用时机，避免预期之外的BrokenBarrierException
 */
public class CyclicBarrierTest4 {

    static CyclicBarrier c = new CyclicBarrier(3);

    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    c.await(2, TimeUnit.SECONDS);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(1);
            }
        });
        thread.start();
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    c.await();
                } catch (Exception e) {
                    throw new RuntimeException("抛出异常" + e.getClass());
                }
                System.out.println(2);
            }
        }).start();
        try {
            //这里等待2秒回导致上面的线程的await超时，触发上述机制
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            c.await();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(c.isBroken());
        }
        System.out.println(3);
    }
}
