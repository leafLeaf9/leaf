package com.gousade.rocketmq.remoting.transaction;

import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Qwen2.5-Max
 * 在 RocketMQ 的事务消息机制中，executeLocalTransaction 方法返回 LocalTransactionState.UNKNOW 时，表示生产者暂时无法确定本地事务的状态。此时，Broker 会根据配置的回查规则，定期向生产者发起事务状态回查请求，以确认该消息的最终状态（提交或回滚）。
 * 回查的时间间隔
 * RocketMQ 的事务消息回查机制中，具体的回查时间间隔是由 Broker 的配置参数决定的。以下是相关的关键配置：
 * <p>
 * transactionCheckMax
 * 该参数定义了 Broker 对一条事务消息进行回查的最大次数。默认值为 15 次 。
 * 如果达到最大回查次数后仍然无法确定事务状态，Broker 会将该消息丢弃。
 * transactionTimeout
 * 该参数定义了事务消息的超时时间，默认值为 6 秒 。
 * 如果生产者在该时间内未明确提交或回滚事务状态，Broker 会开始进行回查。
 * transactionCheckInterval
 * 该参数定义了两次回查之间的时间间隔，默认值为 60 秒 。
 * 也就是说，Broker 会在第一次回查后，每隔 60 秒再次发起回查，直到达到最大回查次数或事务状态被明确。
 * 回查流程总结
 * 当 executeLocalTransaction 返回 LocalTransactionState.UNKNOW 时，Broker 会等待 transactionTimeout 时间后发起第一次回查。
 * 如果第一次回查仍然无法确定事务状态，Broker 会按照 transactionCheckInterval 的时间间隔（默认 60 秒）继续发起回查。
 * 如果回查次数达到 transactionCheckMax（默认 15 次）后仍未确定状态，Broker 会丢弃该消息。
 * 配置调整
 * 如果需要调整回查的时间间隔或最大回查次数，可以在 Broker 的配置文件（如 broker.conf）中修改以下参数：
 * transactionTimeout=6 # 单位：秒
 * transactionCheckMax=15
 * transactionCheckInterval=60 # 单位：秒
 */
public class TransactionListenerImpl implements TransactionListener {
    private AtomicInteger transactionIndex = new AtomicInteger(0);

    private ConcurrentHashMap<String, Integer> localTrans = new ConcurrentHashMap<>();

    @Override
    public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        int value = transactionIndex.getAndIncrement();
        int status = value % 3;
        localTrans.put(msg.getTransactionId(), status);
        return LocalTransactionState.UNKNOW;
//        String tags = msg.getTags();
//        if(StringUtils.contains(tags,"TagA")){
//            return LocalTransactionState.COMMIT_MESSAGE;
//        }else if(StringUtils.contains(tags,"TagB")){
//            return LocalTransactionState.ROLLBACK_MESSAGE;
//        }else{
//            return LocalTransactionState.UNKNOW;
//        }
    }

    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt msg) {
        Integer status = localTrans.get(msg.getTransactionId());
        if (null != status) {
            switch (status) {
                case 0:
                    return LocalTransactionState.UNKNOW;
                case 1:
                    return LocalTransactionState.COMMIT_MESSAGE;
                case 2:
                    return LocalTransactionState.ROLLBACK_MESSAGE;
                default:
                    return LocalTransactionState.COMMIT_MESSAGE;
            }
        }
        return LocalTransactionState.COMMIT_MESSAGE;
    }
}
