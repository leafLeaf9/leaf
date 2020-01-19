package com.gousade.study.rabbitmq;
import com.rabbitmq.client.*;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.apache.commons.lang3.SerializationUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.ShutdownSignalException;

/** 
* @author  作者 ucs_fuqing
* @date 创建时间：2017年8月11日 下午2:39:51 
* @version 1.0 
* @parameter  
* @since  
* @return  
*/
public class QueueConsumer extends PointToPoint implements Runnable,Consumer{

    public QueueConsumer(String pointName) throws IOException, TimeoutException {
        super(pointName);
        // TODO Auto-generated constructor stub
    }
    
    public void run(){
        try {
            channel.basicConsume(pointName,true,this);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void handleConsumeOk(String consumerTag) {
        // TODO Auto-generated method stub
        System.out.println("Consumer "+consumerTag +" registered");
        
    }

    @Override
    public void handleCancelOk(String consumerTag) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void handleCancel(String consumerTag) throws IOException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope env, BasicProperties props, byte[] body) throws IOException {
        // TODO Auto-generated method stub
        Map map = (HashMap)SerializationUtils.deserialize(body);
        System.out.println("Message Number "+ map.get("tagId") + " received.");
//        channel.basicAck(env.getDeliveryTag(), false);
    }

    @Override
    public void handleShutdownSignal(String consumerTag, ShutdownSignalException sig) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void handleRecoverOk(String consumerTag) {
        // TODO Auto-generated method stub
        
    }

}