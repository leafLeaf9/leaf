package com.gousade.rocketmq;

import org.apache.rocketmq.spring.annotation.ExtRocketMQTemplateConfiguration;
import org.apache.rocketmq.spring.core.RocketMQTemplate;

/**
 * 默认一个rocketMQTemplate不支持多个RocketMQLocalTransactionListener 所以一个项目发送多种事务消息需要多个RocketMQTemplate
 */
@ExtRocketMQTemplateConfiguration
public class ExtRocketMQTemplate extends RocketMQTemplate {
}
