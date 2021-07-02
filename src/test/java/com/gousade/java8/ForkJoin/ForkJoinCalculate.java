package com.gousade.java8.ForkJoin;

import java.util.concurrent.RecursiveTask;

/**
 * @author woxigsd@gmail.com
 * @date 2020-10-28 9:19:59
 * @description
 */
public class ForkJoinCalculate extends RecursiveTask<Long> {

    private static final long serialVersionUID = 8722299102114977887L;
    private static final long THRESHOLD = 10000L; // 临界值
    private long start;
    private long end;

    public ForkJoinCalculate(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long length = end - start;

        if (length <= THRESHOLD) {
            long sum = 0;

            for (long i = start; i <= end; i++) {
                sum += i;
            }

            return sum;
        } else {
            long middle = (start + end) / 2;

            ForkJoinCalculate left = new ForkJoinCalculate(start, middle);
            left.fork(); // 拆分，并将该子任务压入线程队列

            ForkJoinCalculate right = new ForkJoinCalculate(middle + 1, end);
            right.fork();

            return left.join() + right.join();
        }
    }

}
