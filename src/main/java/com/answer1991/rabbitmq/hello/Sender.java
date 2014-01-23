package com.answer1991.rabbitmq.hello;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Sender {
	public static void main(String[] args) throws IOException {
		ConnectionFactory cf = new ConnectionFactory();
		cf.setHost("localhost");

		Connection conn = cf.newConnection();
		
		System.out.println(conn.isOpen());
		
		Channel channel = conn.createChannel();
		
		channel.queueDeclare("helloQueue", false, false, false, null);
		
		String message = "您好";
		
		channel.basicPublish("", "helloQueue", false, null, message.getBytes());
		
		channel.close();
		conn.close();
	}
}
