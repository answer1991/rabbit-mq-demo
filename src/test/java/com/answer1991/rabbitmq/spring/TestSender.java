package com.answer1991.rabbitmq.spring;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.DefaultJackson2JavaTypeMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration("classpath:beans.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TestSender {
	@Autowired
	private RabbitTemplate template;
	
	@Autowired
	private RabbitAdmin admin;
	
	@Autowired
	private Jackson2JsonMessageConverter converter;
	
	//@Before
	public void init() {
		admin.deleteQueue("myQueue");
	}
	
	//@Test
	public void test() throws InterruptedException {
		template.convertAndSend("sport.ball.football", "hello");
		Thread.sleep(10000);
	}
	
	@Test
	public void test2() throws InterruptedException {
		TestPojo obj = new TestPojo();
		obj.setId("fadsf");
		obj.setName("fasfasf");
		obj.setPets(new ArrayList<String>());
		obj.getPets().add("falsjfl");
		obj.getPets().add("fasfddsaf");
		
		/*
		Message message = converter.toMessage(obj, new MessageProperties());
		String typeId = (String) message.getMessageProperties().getHeaders().get("__TypeId__");
		System.out.println(typeId);
		System.out.println(((DefaultJackson2JavaTypeMapper)converter.getJavaTypeMapper()).getIdClassMapping().get(typeId));
		//System.out.println(((DefaultJackson2JavaTypeMapper)converter.getJavaTypeMapper()).getIdClassMapping().get(message.getMessageProperties().getHeaders().get("__TypeId__")));
		//System.out.println(message.getMessageProperties().getHeaders().get("__TypeId__"));
		*/
		template.convertAndSend("sport.ball.football", obj);
		Thread.sleep(1000);
	}
}
