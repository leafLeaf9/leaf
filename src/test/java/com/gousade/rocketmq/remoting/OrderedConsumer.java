package com.gousade.rocketmq.remoting;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.nio.charset.StandardCharsets;

/**
 * 启动两个该类实例观察OrderedProducer发送的消息 接收顺序
 */
public class OrderedConsumer {
    public static void main(String[] args) throws MQClientException, MQBrokerException, RemotingException, InterruptedException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("my-consumer-group2");
        consumer.setNamesrvAddr("localhost:9876");
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer.subscribe("OrderTopicTest", "*");
        consumer.registerMessageListener((MessageListenerOrderly) (msgs, context) -> {
            context.setAutoCommit(true);
            msgs.forEach(msg -> System.out.println("收到消息：" + new String(msg.getBody(), StandardCharsets.UTF_8)));
            return ConsumeOrderlyStatus.SUCCESS;
        });
        // 下面这种是并发消费
//        consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
//            for(MessageExt msg:msgs){
//                System.out.println("消息内容："+new String(msg.getBody()));
//            }
//            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
//        });
        consumer.start();
    }
}
