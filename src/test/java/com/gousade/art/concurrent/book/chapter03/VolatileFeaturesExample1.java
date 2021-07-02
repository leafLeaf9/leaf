package com.gousade.art.concurrent.book.chapter03;

import java.util.ArrayList;
import java.util.List;

class VolatileFeaturesExample1 {
    long vl = 0L; // 64λ��long����ͨ����

    public synchronized void set(long l) {//�Ե�������ͨ������д��ͬһ����ͬ��
        vl = l;
    }

    public void getAndIncrement() { //��ͨ��������
        long temp = get(); //������ͬ���Ķ�����
        temp += 1L; //��ͨд����
        set(temp); //������ͬ����д����
    }

    public synchronized long get() { //�Ե�������ͨ�����Ķ���ͬһ����ͬ��
        return vl;
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
