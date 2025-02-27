package com.gousade.captcha;


import java.util.concurrent.atomic.AtomicStampedReference;

public class MoleculeRenderTests {
	private static final int CORE_POOL_SIZE = 5;
	private static final int MAX_POOL_SIZE = 10;
	private static final int QUEUE_CAPACITY = 100;
	private static final Long KEEP_ALIVE_TIME = 1L;

	public static void main(String[] args) throws InterruptedException {

// 创建一个 AtomicStampedReference 对象，初始值为 "SnailClimb"，初始版本号为 1
		AtomicStampedReference<String> asr = new AtomicStampedReference<>("SnailClimb", 1);

// 打印初始值和版本号
		int[] initialStamp = new int[1];
		String initialRef = asr.get(initialStamp);
		System.out.println("Initial Reference: " + initialRef + ", Initial Stamp: " + initialStamp[0]);

// 更新值和版本号
		int oldStamp = initialStamp[0];
		String oldRef = initialRef;
		String newRef = "Daisy";
		int newStamp = oldStamp + 1;

		boolean isUpdated = asr.compareAndSet(oldRef, newRef, oldStamp, newStamp);
		System.out.println("Update Success: " + isUpdated);

// 打印更新后的值和版本号
		int[] updatedStamp = new int[1];
		String updatedRef = asr.get(updatedStamp);
		System.out.println("Updated Reference: " + updatedRef + ", Updated Stamp: " + updatedStamp[0]);

// 尝试用错误的版本号更新
		boolean isUpdatedWithWrongStamp = asr.compareAndSet(newRef, "John", newStamp, newStamp + 1);
		System.out.println("Update with Wrong Stamp Success: " + isUpdatedWithWrongStamp);

// 打印最终的值和版本号
		int[] finalStamp = new int[1];
		String finalRef = asr.get(finalStamp);
		System.out.println("Final Reference: " + finalRef + ", Final Stamp: " + finalStamp[0]);
	}

}
