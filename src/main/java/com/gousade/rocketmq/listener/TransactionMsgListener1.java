package com.gousade.rocketmq.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.messaging.Message;

import java.nio.charset.StandardCharsets;

/**
 * 这里使用自定义的extRocketMQTemplate
 */
@Slf4j
@RocketMQTransactionListener(rocketMQTemplateBeanName = "extRocketMQTemplate")
public class TransactionMsgListener1 implements RocketMQLocalTransactionListener {

    /**
     * 执行本地事务
     */
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg,
                                                                 Object obj) {
        log.info("start invoke local rocketMQ transaction");
        RocketMQLocalTransactionState resultState = RocketMQLocalTransactionState.COMMIT;

        try {
            //处理业务
            String jsonStr = new String((byte[]) msg.getPayload(), StandardCharsets.UTF_8);
            log.info("invoke local transaction msg content：{}", jsonStr);
        } catch (Exception e) {
            log.error("invoke local mq trans error", e);
            resultState = RocketMQLocalTransactionState.UNKNOWN;
        }

        return resultState;
    }

    /**
     * 检查本地事务的状态
     */
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
        log.info("start check Local rocketMQ transaction");

        RocketMQLocalTransactionState resultState = RocketMQLocalTransactionState.COMMIT;

        try {
            String jsonStr = new String((byte[]) msg.getPayload(), StandardCharsets.UTF_8);
            log.info("check trans msg content：{}", jsonStr);
        } catch (Exception e) {
            resultState = RocketMQLocalTransactionState.ROLLBACK;
        }
        return resultState;
    }
}

