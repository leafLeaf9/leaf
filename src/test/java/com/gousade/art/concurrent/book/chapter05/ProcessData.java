package com.gousade.art.concurrent.book.chapter05;

import org.junit.jupiter.api.Test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author woxigousade <woxigsd@gmail.com>
 * @date 2021/10/21
 */
public class ProcessData {
	private static final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
	private static final Lock readLock = rwl.readLock();
	private static final Lock writeLock = rwl.writeLock();
	private volatile boolean update = false;

	/**
	 * 锁降级是指把持住（当前拥有的）写锁，再获取到读锁，随后释放（先前拥有的）写锁的过程
	 */
	@Test
	public void processData() {
		readLock.lock();
		if (!update) {
			// 必须先释放读锁
			readLock.unlock();
			// 锁降级从写锁获取到开始
			writeLock.lock();
			try {
				if (!update) {
					// 准备数据的流程（略）
					update = true;
				}
				readLock.lock();
			} finally {
				writeLock.unlock();
			}
			// 锁降级完成，写锁降级为读锁
		}
		try {
			// 使用数据的流程（略）
		} finally {
			readLock.unlock();
		}
	}
}