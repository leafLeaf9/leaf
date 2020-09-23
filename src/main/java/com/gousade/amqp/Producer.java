package com.gousade.amqp;

import org.apache.commons.lang3.SerializationUtils;

import java.io.IOException;
import java.io.Serializable;
import java.util.concurrent.TimeoutException;

public class Producer extends PointToPoint {

    public Producer(String pointName) throws IOException, TimeoutException {
        super(pointName);
    }

    /**
     * @Title: sendMessage @Description: 生产消息 @param @param Object @param @throws
     * IOException 设定文件 @return void 返回类型 @throws
     */
    public void sendMessage(Serializable Object) throws IOException {
        channel.basicPublish("", pointName, null, SerializationUtils.serialize(Object));
    }
}