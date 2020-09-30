package com.gousade.amqp;


import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeoutException;

/**
 * @author ucs_fuqing
 * @version 1.0
 * @date 2017年8月11日 下午2:44:59
 * @parameter
 * @return
 * @since
 */
public class MainTest {

	public MainTest() throws IOException, TimeoutException {

	}

	@Test
	public void pstest() throws IOException, TimeoutException {
		QueueConsumer consumer = new QueueConsumer("testgouqueue");
		Thread cuThread = new Thread(consumer);
		QueueConsumer consumer2 = new QueueConsumer("testgouqueue");
		Thread cuThread2 = new Thread(consumer2);
		cuThread.start();
		cuThread2.start();

		Producer producer = new Producer("testgouqueue");
		int i = 0;
		while (i < 10000) {
			HashMap<String, Object> hm = new HashMap<>();
			hm.put("tagId", i);
			producer.sendMessage(hm);
			System.out.println("发送第" + i + "消息");
			i++;
		}
	}

	/* public static void main(String[] args) throws IOException, TimeoutException {
	    new MainTest();
	}*/
}