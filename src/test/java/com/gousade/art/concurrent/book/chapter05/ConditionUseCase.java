package com.gousade.art.concurrent.book.chapter05;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author woxigousade <woxigsd@gmail.com>
 * @date 2021/10/26
 */
public class ConditionUseCase {
	Lock lock = new ReentrantLock();
	Condition condition = lock.newCondition();

	public void conditionWait() throws InterruptedException {
		lock.lock();
		try {
			condition.await();
		} finally {
			lock.unlock();
		}
	}

	public void conditionSignal() throws InterruptedException {
		lock.lock();
		try {
			condition.signal();
		} finally {
			lock.unlock();
		}
	}
}