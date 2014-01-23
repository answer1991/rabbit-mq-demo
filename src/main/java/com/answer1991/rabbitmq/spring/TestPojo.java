package com.answer1991.rabbitmq.spring;

import java.util.List;

public class TestPojo {
	private String name;
	private String id;
	private List<String> pets;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<String> getPets() {
		return pets;
	}
	public void setPets(List<String> pets) {
		this.pets = pets;
	}
	
}
