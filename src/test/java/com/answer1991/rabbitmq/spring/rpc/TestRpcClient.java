package com.answer1991.rabbitmq.spring.rpc;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration("classpath:com/answer1991/rabbitmq/spring/rpc/client.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TestRpcClient {
	@Autowired
	private AccountServiceClient client;
	
	@Test
	public void test() {
		Account account = client.getAccountById("055574");
		Assert.assertNotNull(account);
		Assert.assertEquals("Joe Chen", account.getName());
	}
	
	@Test
	public void test2() {
		Account account = client.getAccountById("05557");
		Assert.assertNull(account);
	}
}
