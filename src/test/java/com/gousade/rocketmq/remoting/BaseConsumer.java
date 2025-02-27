package com.gousade.rocketmq.remoting;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.remoting.exception.RemotingException;

public class BaseConsumer {
    public static void main(String[] args) throws MQClientException, MQBrokerException, RemotingException, InterruptedException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("my-consumer-group2");
        consumer.setNamesrvAddr("localhost:9876");
        consumer.subscribe("BatchTest", "*");
        consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
//            msgs.forEach(msg -> System.out.println("收到消息：" + new String(msg.getBody(), StandardCharsets.UTF_8)));
            msgs.forEach(msg -> System.out.println("收到消息：" + msg));
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });
        consumer.start();
    }
}
