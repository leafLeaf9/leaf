package com.gousade.art.concurrent.book.chapter05;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author woxigousade <woxigsd@gmail.com>
 * @date 2021/10/26
 */
@Slf4j
public class BoundedQueueTest {

	@Test
	public void test() throws InterruptedException {
		CountDownLatch latch = new CountDownLatch(2);
		BoundedQueue<String> queue = new BoundedQueue<>(3);
		new Thread(() -> {
			try {
				queue.add("1");
				queue.add("2");
				queue.add("3");
				log.info(Arrays.toString(queue.getItems()));
				queue.add("4");
				log.info(Arrays.toString(queue.getItems()));
				latch.countDown();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}, "添加元素线程").start();
		Thread.sleep(1000);
		new Thread(() -> {
			try {
				log.info(Arrays.toString(queue.getItems()));
				queue.remove();
				latch.countDown();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}, "移除元素线程").start();
		latch.await();
		log.info(Arrays.toString(queue.getItems()));
	}

	@Test
	public void test2() throws InterruptedException {
		Lock lock = new ReentrantLock();
		Condition notEmpty = lock.newCondition();
		lock.lock();
		try {
//		notEmpty.await();
			notEmpty.signal();
		} finally {
			lock.unlock();
		}
	}
}
