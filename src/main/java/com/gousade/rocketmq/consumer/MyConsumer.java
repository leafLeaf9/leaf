package com.gousade.rocketmq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.annotation.SelectorType;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RocketMQMessageListener(consumerGroup = "my-boot-consumer-group1", topic = "my-boot-topic", selectorType = SelectorType.TAG,
        selectorExpression = "*", consumeMode = ConsumeMode.CONCURRENTLY, messageModel = MessageModel.CLUSTERING)
public class MyConsumer implements RocketMQListener<String> {
    @Override
    public void onMessage(String message) {
        log.info("收到消息：{}", message);
    }
}
