package com.answer1991.rabbitmq.spring.listener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;

import com.answer1991.rabbitmq.spring.TestPojo;
import com.fasterxml.jackson.databind.ObjectMapper;


public class Listener implements MessageListener {
	@Autowired
	private Jackson2JsonMessageConverter converter;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Override
	public void onMessage(Message msg) {
		MessageProperties properties = msg.getMessageProperties();
		//properties.get
		System.out.println(new String(msg.getBody()));
		
		Object obj = converter.fromMessage(msg);
		
		if(obj instanceof TestPojo) {
			//System.out.println(((TestPojo) obj).getId());
			System.out.println("received a TestPojo request");
		}
	}
	/*
	public void onReceive(Message msg) {
		MessageProperties properties = msg.getMessageProperties();
		//properties.get
		System.out.println(new String(msg.getBody()));
	}
	*/
}
