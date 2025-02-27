package com.gousade.rocketmq.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MyProducer {
    private final RocketMQTemplate rocketMQTemplate;

    public MyProducer(RocketMQTemplate rocketMQTemplate) {
        this.rocketMQTemplate = rocketMQTemplate;
    }

    public void sendMessage(String topic, String message) {
//        //通过MessageBuilder构建消息
//        Message<String> payload = MessageBuilder.withPayload(message).build();
//        //同步发送该消息，获取发送结果
//        SendResult result = rocketMQTemplate.syncSend(topic, payload);
        rocketMQTemplate.convertAndSend(topic, message);
    }

    public void sendMessageASync(String topic, String message) {
        rocketMQTemplate.asyncSend(topic, message, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.info("Send message success: {}", sendResult);
            }

            @Override
            public void onException(Throwable e) {
                log.error("Send message error", e);
            }
        });
    }

    public void sendOrderedMessage(String topic, String message, String orderKey) {
        Message<String> payload = MessageBuilder.withPayload(message).build();
        SendResult sendResult = rocketMQTemplate.syncSendOrderly(topic, payload, orderKey);
//        SendResult sendResult = rocketMQTemplate.syncSendOrderly(topic, message, orderKey);
        log.info("Send ordered message result: {}", sendResult);
    }

    public void sendDelayedMessage(String topic, String message, int delayTime) {
        SendResult sendResult = rocketMQTemplate.syncSendDelayTimeMills(topic, message, delayTime);
        log.info("Send ordered message result: {}", sendResult);
    }

    public void sendMessageInTransaction(String topic, String msg) {
        String[] tags = new String[]{"TagA", "TagB", "TagC", "TagD", "TagE"};
        for (int i = 0; i < 10; i++) {
            Message<String> message = MessageBuilder.withPayload(msg).setHeader("testHeadName", "testHeadValue").build();
            //topic和tag整合在一起
            String destination = topic + ":" + tags[i % tags.length];
            //第一个destination是消息要发送的目的地topic，第二个destination消息携带的业务数据
            TransactionSendResult sendResult = rocketMQTemplate.sendMessageInTransaction(destination, message, destination);
            log.info("发送结果: {}", sendResult);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

}
