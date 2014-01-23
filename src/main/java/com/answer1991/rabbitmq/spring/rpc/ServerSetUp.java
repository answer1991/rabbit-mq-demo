package com.answer1991.rabbitmq.spring.rpc;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ServerSetUp {
	public static void main(String[] args) throws InterruptedException {
		AbstractApplicationContext cxt = new ClassPathXmlApplicationContext("classpath:com/answer1991/rabbitmq/spring/rpc/server.xml");
		cxt.registerShutdownHook();
		
		while(true) {
			Thread.sleep(1000);
		}
	}
}
