package com.gousade.rocketmq.producer;


import com.gousade.rocketmq.ExtRocketMQTemplate;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TransactionProduce1 {
    @Autowired
    private ExtRocketMQTemplate extRocketMQTemplate;

    public void sendTransactionMessage(String msg) {
        log.info("product start sendTransMessage msg:{}", msg);

        Message message = new Message();
        message.setTopic("test-tx-rocketmq1");
        message.setBody("this is tx message".getBytes());
        message.setTags("TAGA");
        TransactionSendResult result = extRocketMQTemplate.sendMessageInTransaction("test-tx-rocketmq1",
                MessageBuilder.withPayload(message).build(), msg);

        //发送状态
        String sendStatus = result.getSendStatus().name();
        // 本地事务执行状态
        String localTxState = result.getLocalTransactionState().name();
        log.info("send tx message sendStatus:{},localTXState:{}", sendStatus, localTxState);
    }
}
