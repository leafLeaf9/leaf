package com.gousade.amqp;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author ucs_fuqing
 * @date 2017年8月11日 下午2:21:27
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
public abstract class PointToPoint {
	protected Channel channel;
	protected Connection connection;
	protected String pointName;

	/**
	 * 获取一个队列的连接
	 * 
	 * @param pointName
	 * @throws IOException
	 * @throws TimeoutException
	 */
	public PointToPoint(String pointName) throws IOException, TimeoutException {
		this.pointName = pointName;

		// 创建连接工厂
		ConnectionFactory cf = new ConnectionFactory();

		// 设置rabbitmq服务器地址
		cf.setHost("localhost");

		// 设置rabbitmq服务器用户名
		cf.setUsername("gousade");

		// 设置rabbitmq服务器密码
		cf.setPassword("gousade");

		// 获取一个新的连接
		connection = cf.newConnection();

		// 创建一个通道
		channel = connection.createChannel();

		// 申明一个队列，如果这个队列不存在，将会被创建
		channel.queueDeclare(pointName, false, false, false, null);
	}

	/**
	 * @throws TimeoutException
	 * 
	 * @Title: close @Description: 其实在程序完成时一般会自动关闭连接，但是这里提供手动操作的入口， @param @throws
	 * IOException 设定文件 @return void 返回类型 @throws
	 */
	public void close() throws IOException, TimeoutException {
		this.channel.close();
		this.connection.close();
	}
}