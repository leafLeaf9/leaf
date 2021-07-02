package com.gousade.amqp;

import com.gousade.commonutils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author woxigsd@gmail.com
 * @date 2020-9-4 9:41:38
 * @description
 */
@Slf4j
//@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitMQTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * exchange.direct单播(点对点)
     */
    @Test
    public void contrextloads() {
        // Message需要自己构造一个;定义消息体内容和消息头
        // rabbitTemplate.send(exchange, routingKey, message);
        // object默认当成消息体，只需要传入要发送的对象，自动序列化发送给rabbitmq
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("msg", "这是第一个消息");
        map.put("data", Arrays.asList("helloworld", 123, true));
        rabbitTemplate.convertAndSend("exchange.direct", "gousade.news",
                ResponseResult.renderSuccess().message("发送点对点消息"));
        rabbitTemplate.convertAndSend("exchange.direct", "gousade.news", "aa");
        rabbitTemplate.convertAndSend("exchange.direct", "gousade.news", map);
    }

    @Test
    public void receive() {
        Object o = rabbitTemplate.receiveAndConvert("gousade.news");
        log.info(o.getClass().toString());
        log.info(o.toString());
    }

    /**
     * exchange.fanout广播 fanout直接给所有绑定的队列发送消息 所以不填路由键也一样
     */
    @Test
    public void fanoutSendMsg() {
        rabbitTemplate.convertAndSend("exchange.fanout", "",
                ResponseResult.renderError().message("调用exchange.fanout发送广播消息"));
    }

    /**
     * topic 匹配路由键为gousade.#的所有队列 #代表0-多个单词
     */
    @Test
    public void topicSend1() {
        String context = "hi, i am topic message 1";
        log.info("topicSender : " + context);
        rabbitTemplate.convertAndSend("exchange.topic", "gousade.", context);
        rabbitTemplate.convertAndSend("exchange.topic", "gousade.xxx.sss", context);
    }

    /**
     * topic 匹配路由键为*.news的所有队列 *代表1个单词 以.为分界 sss.xxx.news中代表sss.xxx代表两个单词
     * 而sss-xxx是一个单词
     * 被点号.分隔开的每一段独立的字符串称为一个单词
     */
    @Test
    public void topicSend2() {
        String context = "hi, i am topic message 2 to sss-xxx.news";
        String context2 = "hi, i am topic message 2 to sss.xxx.news";
        log.info("topicSender2 : " + context);
        rabbitTemplate.convertAndSend("exchange.topic", "sss-xxx.news", context);
        rabbitTemplate.convertAndSend("exchange.topic", "sss.xxx.news", context2);// 没有接收者 因为没有对应的路由键绑定信息
    }

}
