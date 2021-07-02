package com.gousade.controller;

import com.gousade.commonutils.ResponseResult;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author woxigsd@gmail.com
 * @date 2020-9-4 14:24:23
 * @description
 */
@Api(tags = "rabbitMQ")
@Slf4j
@RestController
@RequestMapping(value = "/rabbitmq")
public class RabbitMQController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private AmqpAdmin amqpAdmin;

    @RequestMapping(value = "/contrextloads", method = RequestMethod.GET)
    public ResponseResult contrextloads() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("msg", "这是第一个消息");
        map.put("data", Arrays.asList("helloworld", 123, true));
        rabbitTemplate.convertAndSend("exchange.direct", "gousade.news",
                ResponseResult.renderSuccess().message("发送点对点消息"));
        rabbitTemplate.convertAndSend("exchange.direct", "gousade.news", "aa");
        rabbitTemplate.convertAndSend("exchange.direct", "gousade.news", map);
        rabbitTemplate.convertAndSend("exchange.fanout", "",
                ResponseResult.renderError().message("调用exchange.fanout发送广播消息"));
        log.info("发送消息成功。");
        return ResponseResult.renderSuccess().message("发送消息成功。");
    }

    /**
     * public Binding(String destination, DestinationType destinationType, String
     * exchange, String routingKey,
     *
     * @Nullable Map<String, Object> arguments)
     * 目的地(一般为queue)，目的地类型，exchange,路由键，参数(一般不填)
     */
    @RequestMapping(value = "/createExchangeAndQueueAndBind", method = RequestMethod.GET)
    public ResponseResult createExchangeAndQueueAndBind() {
        amqpAdmin.declareExchange(new DirectExchange("exchange.direct"));
        amqpAdmin.declareExchange(new FanoutExchange("exchange.fanout"));
        amqpAdmin.declareExchange(new TopicExchange("exchange.topic"));
        log.info("创建exchanges成功");
        amqpAdmin.declareQueue(new Queue("amqpAdmin.queue"));
        amqpAdmin.declareQueue(new Queue("gousade"));
        amqpAdmin.declareQueue(new Queue("gousade.news"));
        amqpAdmin.declareQueue(new Queue("gousade.emps"));
        amqpAdmin.declareQueue(new Queue("woxigsd.news"));
        log.info("创建queque成功");
        amqpAdmin.declareBinding(
                new Binding("gousade", Binding.DestinationType.QUEUE, "exchange.direct", "gousade", null));
        amqpAdmin.declareBinding(
                new Binding("gousade.news", Binding.DestinationType.QUEUE, "exchange.direct", "gousade.news", null));
        amqpAdmin.declareBinding(
                new Binding("gousade.emps", Binding.DestinationType.QUEUE, "exchange.direct", "gousade.emps", null));
        amqpAdmin.declareBinding(
                new Binding("woxigsd.news", Binding.DestinationType.QUEUE, "exchange.direct", "woxigsd.news", null));
        log.info("exchange.direct绑定queue成功");
        // fanout不在乎路由键是什么
        amqpAdmin.declareBinding(
                new Binding("gousade", Binding.DestinationType.QUEUE, "exchange.fanout", "gousade", null));
        amqpAdmin.declareBinding(
                new Binding("gousade.news", Binding.DestinationType.QUEUE, "exchange.fanout", "", null));
        amqpAdmin.declareBinding(
                new Binding("gousade.emps", Binding.DestinationType.QUEUE, "exchange.fanout", "", null));
        amqpAdmin.declareBinding(
                new Binding("woxigsd.news", Binding.DestinationType.QUEUE, "exchange.fanout", "woxigsd.news", null));
        log.info("exchange.fanout绑定queue成功");
        amqpAdmin.declareBinding(
                new Binding("gousade", Binding.DestinationType.QUEUE, "exchange.topic", "gousade.#", null));
        amqpAdmin.declareBinding(
                new Binding("gousade.news", Binding.DestinationType.QUEUE, "exchange.topic", "gousade.#", null));
        amqpAdmin.declareBinding(
                new Binding("gousade.news", Binding.DestinationType.QUEUE, "exchange.topic", "*.news", null));
        amqpAdmin.declareBinding(
                new Binding("gousade.emps", Binding.DestinationType.QUEUE, "exchange.topic", "gousade.#", null));
        amqpAdmin.declareBinding(
                new Binding("woxigsd.news", Binding.DestinationType.QUEUE, "exchange.topic", "*.news", null));
        log.info("exchange.topic绑定queue成功");
        return ResponseResult.renderSuccess().message("创建exchanges、queues,并绑定路由键成功");
    }

}
