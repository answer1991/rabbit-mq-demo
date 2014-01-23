package com.answer1991.rabbitmq.spring;

import org.springframework.amqp.rabbit.core.RabbitTemplate.ConfirmCallback;
import org.springframework.amqp.rabbit.support.CorrelationData;

public class TestPublishConfirm implements ConfirmCallback {
	@Override
	public void confirm(CorrelationData correlationData, boolean ack) {
		//System.out.println("confirm callback : " + correlationData.getId());
		System.out.println(ack);
	}
}
