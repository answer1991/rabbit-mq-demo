package com.answer1991.rabbitmq.spring;

import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.ClassMapper;

public class MyClassMapper implements ClassMapper {

	@Override
	public void fromClass(Class<?> clazz, MessageProperties properties) {
		if(clazz.equals(TestPojo.class)) {
			properties.setHeader("clazz", "testPojo");
		}
	}

	@Override
	public Class<?> toClass(MessageProperties properties) {
		if(properties.getHeaders().get("clazz").equals("testPojo")) {
			return TestPojo.class;
		}
		else {
			return String.class;
		}
	}

}
