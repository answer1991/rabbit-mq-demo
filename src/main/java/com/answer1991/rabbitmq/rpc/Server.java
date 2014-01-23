package com.answer1991.rabbitmq.rpc;

import java.io.IOException;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.QueueingConsumer.Delivery;
import com.rabbitmq.client.ShutdownSignalException;

public class Server {
	public static void main(String[] args) throws IOException, ShutdownSignalException, ConsumerCancelledException, InterruptedException {
		ConnectionFactory cf = new ConnectionFactory();
		cf.setHost("localhost");
		
		Connection conn = cf.newConnection();
		Channel ch = conn.createChannel();
		
		ch.queueDeclare("rpc queue", false, false, false, null);
		ch.basicQos(1);
		
		QueueingConsumer consumer = new QueueingConsumer(ch);
		ch.basicConsume("rpc queue", false, consumer);
		
		while(true) {
			Delivery delivery = consumer.nextDelivery();
			String msg = new String(delivery.getBody());
			System.out.println("get request : " + msg);
			Thread.sleep(5000);
			
			String correlationId = delivery.getProperties().getCorrelationId();
			BasicProperties responseProperties = new BasicProperties.Builder().correlationId(correlationId).build();
			
			String responseQueue = delivery.getProperties().getReplyTo();
			
			ch.basicPublish("", responseQueue, responseProperties, getResponseMsg(msg).getBytes());
			ch.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
		}
	}
	
	public static String getResponseMsg(String inputMsg) {
		return "echo ," + inputMsg;
	}
}
