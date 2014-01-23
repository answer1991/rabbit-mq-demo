package com.answer1991.rabbitmq.spring.rpc;


public class AccountServiceClient implements AccountService {
	private AccountService service;
	
	@Override
	public Account getAccountById(String id) {
		return service.getAccountById(id);
	}

	public void setService(AccountService service) {
		this.service = service;
	}
	
}
