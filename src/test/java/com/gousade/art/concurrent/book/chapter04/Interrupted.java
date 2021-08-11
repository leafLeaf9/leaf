package com.gousade.art.concurrent.book.chapter04;

import java.util.concurrent.TimeUnit;

/**
 * {@link java.lang.Thread#interrupted}这个静态方法实际就是判断中断标志并清理掉中断标志,这样后面再判断就是未中断了。
 * 对于处在休眠(sleep)、等待(wait,join)、IO阻塞状态的线程, 可以认为中断{@link java.lang.Thread#interrupt}就是"打断/唤醒",
 * 它的中断状态将被清除,线程会收到一个异常({@link java.lang.InterruptedException}),
 * 此异常由使线程阻塞的API(sleep、wait、join)抛出。线程内可以捕获这个异常继续运行,也可以自主结束,也可以干别的事情。
 * <p>
 * 如果线程中不存在任何阻塞、休眠、等待的操作,则当线程被中断时, 仅仅只是设置了一个中断标记(interrupted flag),
 * 线程内可以通过{@link Thread#isInterrupted()}或者{@link java.lang.Thread#interrupted}判断这个中断标记(是否被中断),
 * 然后自主决定做任何事情,当然,这种情况下,Runnable.run方法里的内容也可以完全忽视中断标志,不做任何判断,那线程不会受到任何影响。
 *
 * @author woxigousade
 * @date 2021/7/2
 */
public class Interrupted {

    public static void main(String[] args) throws InterruptedException {
        // sleepThread不停的尝试睡眠
        Thread sleepThread = new Thread(new SleepRunner(), "SleepThread");
        sleepThread.setDaemon(true);
        // busyThread不停的运行
        Thread busyThread = new Thread(new BusyRunner(), "BusyThread");
        busyThread.setDaemon(true);
        sleepThread.start();
        busyThread.start();
        // 休眠5秒,让sleepThread和busyThread充分运行
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sleepThread.interrupt();
        busyThread.interrupt();
        System.out.println("SleepThread interrupted is " + sleepThread.isInterrupted());
        System.out.println("BusyThread interrupted is " + busyThread.isInterrupted());
        // 防止sleepThread和busyThread立刻退出
        TimeUnit.SECONDS.sleep(2);
    }

    static class SleepRunner implements Runnable {
        @Override
        public void run() {
            while (true) {
                SleepUtils.second(10);
            }
        }
    }

    static class BusyRunner implements Runnable {
        @Override
        public void run() {
            while (true) {
            }
        }
    }
}
