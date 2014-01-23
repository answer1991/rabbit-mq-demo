package com.answer1991.rabbitmq.rpc;

import java.io.IOException;
import java.util.UUID;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.AMQP.Queue.DeclareOk;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.QueueingConsumer.Delivery;
import com.rabbitmq.client.ShutdownSignalException;

public class Client {
	public static void main(String[] args) throws IOException, ShutdownSignalException, ConsumerCancelledException, InterruptedException {
		ConnectionFactory cf = new ConnectionFactory();
		cf.setHost("localhost");
		
		Connection conn = cf.newConnection();
		Channel channel = conn.createChannel();
		
		DeclareOk ok = channel.queueDeclare();
		String returnQueueName = ok.getQueue();
		
		QueueingConsumer consumer = new QueueingConsumer(channel);
		channel.basicConsume(returnQueueName, true, consumer);
		
		String correlationId = UUID.randomUUID().toString();
		BasicProperties proeprties = new BasicProperties.Builder().correlationId(correlationId).replyTo(returnQueueName).build();
		
		channel.basicPublish("", "rpc queue", proeprties, "hello".getBytes());
		
		while(true) {
			Delivery delivery = consumer.nextDelivery();
			if(delivery.getProperties().getCorrelationId().equals(correlationId)) {
				System.out.println("get response : " +new String(delivery.getBody()));
				break;
			}
		}
		
		System.out.println("fasdfsa");
		
		conn.close();
	}
}
