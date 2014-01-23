package com.answer1991.rabbitmq.spring;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ReturnCallback;

public class TestReturnCallBack implements ReturnCallback {
	@Override
	public void returnedMessage(Message message, int replyCode, String replyText,
	          String exchange, String routingKey) {
		System.out.println("returned message : " + new String(message.getBody()));
		System.out.println(replyCode);
		System.out.println(replyText);
		System.out.println(exchange);
		System.out.println(routingKey);
	}
}
