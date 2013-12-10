package com.example.helloServlet.form;

import org.apache.struts.action.ActionForm;

public class HelloForm extends ActionForm{
	String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
