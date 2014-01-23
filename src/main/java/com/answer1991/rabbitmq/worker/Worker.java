package com.answer1991.rabbitmq.worker;

import java.io.IOException;

import javax.swing.plaf.SliderUI;

import com.rabbitmq.client.AMQP.Basic.Consume;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.ShutdownSignalException;

public class Worker {
	public static void main(String[] args) throws IOException, ShutdownSignalException, ConsumerCancelledException, InterruptedException {
		ConnectionFactory cf = new ConnectionFactory();
		cf.setHost(Values.HOST);
		Connection conn = cf.newConnection();
		
		Channel ch = conn.createChannel();
		ch.basicQos(1);
		
		ch.queueDeclare(Values.QUEUE_WORKER, false, false, false, null);
		
		QueueingConsumer consumer = new QueueingConsumer(ch);
		
		ch.basicConsume(Values.QUEUE_WORKER, false, consumer);
		
		while(true) {
			QueueingConsumer.Delivery delivery = consumer.nextDelivery();
			
			System.out.println("worker : " + new String(delivery.getBody()));
			
			Thread.sleep(4000);
			
			ch.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
		}
	}
}
