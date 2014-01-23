package com.answer1991.rabbitmq.spring.rpc;

public class AccountServiceImpl implements AccountService {
	@Override
	public Account getAccountById(String id) {
		if("055574".equals(id)) {
			Account account = new Account();
			account.setId(id);
			account.setName("Joe Chen");
			return account;
		}
		return null;
	}
}
