package com.gousade.art.concurrent.book.chapter03;

import java.util.ArrayList;
import java.util.List;

class VolatileFeaturesExample {
    volatile long vl = 0L; //ʹ��volatile����64λ��long�ͱ���

    public void set(long l) {
        vl = l; //����volatile������д
    }

    public void getAndIncrement() {
        vl++; //���ϣ������volatile�����Ķ�/д
    }

    public long get() {
        return vl; //����volatile�����Ķ�
    }

    public static void main(String[] args) {
        final VolatileFeaturesExample cas = new VolatileFeaturesExample();
        List<Thread> ts = new ArrayList<Thread>(600);
        long start = System.currentTimeMillis();
        for (int j = 0; j < 100; j++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 10000; i++) {
                        cas.getAndIncrement();
                    }
                }
            });
            ts.add(t);
        }
        for (Thread t : ts) {
            t.start();

        }
        // �ȴ������߳�ִ�����
        for (Thread t : ts) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        System.out.println(cas.get());
        System.out.println(System.currentTimeMillis() - start);
    }
}
