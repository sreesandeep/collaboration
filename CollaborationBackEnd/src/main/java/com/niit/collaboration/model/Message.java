package com.niit.collaboration.model;

public class Message {

	private String message;
	private String name;
	
	private int id;
	
	public Message() {
		
	}

	public Message(int id , String message) {
		this.id = id;
		this.message = message;
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
}
