package com.yc.qq.ui;

import java.util.ArrayList;
import java.util.List;

public class Users {
	
	private String uname;
	private List<String> messages =new ArrayList<>();

	
	public Users(String uname, List<String> messages) {
		super();
		this.uname = uname;
		this.messages = messages;
	}

	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

	public Users(String uname) {
		super();
		this.uname = uname;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}
	

}
