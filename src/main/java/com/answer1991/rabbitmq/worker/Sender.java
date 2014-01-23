package com.answer1991.rabbitmq.worker;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Sender {
	public static void main(String[] args) throws IOException {
		ConnectionFactory cf = new ConnectionFactory();
		cf.setHost(Values.HOST);
		Connection conn = cf.newConnection();
		
		Channel channel = conn.createChannel();
		
		channel.queueDeclare(Values.QUEUE_WORKER, false, false, false, null);
		
		for(int i = 0; i < 10; i ++) {
			String message = "您好" + i;
			channel.basicPublish("", Values.QUEUE_WORKER, false, null, message.getBytes());
		}
		
		channel.close();
		conn.close();
	}
}
