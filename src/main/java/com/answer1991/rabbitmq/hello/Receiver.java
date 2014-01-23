package com.answer1991.rabbitmq.hello;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.ShutdownSignalException;

public class Receiver {
	public static void main(String[] args) throws IOException, ShutdownSignalException, ConsumerCancelledException, InterruptedException {
		ConnectionFactory cf = new ConnectionFactory();
		cf.setHost("localhost");

		Connection conn = cf.newConnection();
		
		System.out.println(conn.isOpen());
		
		Channel channel = conn.createChannel();
		
		channel.queueDeclare("helloQueue", false, false, false, null);
		
		QueueingConsumer consumer = new QueueingConsumer(channel);
		
		channel.basicConsume("helloQueue", false, consumer);
		
		while(true) {
			QueueingConsumer.Delivery delivery = consumer.nextDelivery();
			String msg = new String(delivery.getBody());
			System.out.println(msg);
			channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
		}
	}
}
