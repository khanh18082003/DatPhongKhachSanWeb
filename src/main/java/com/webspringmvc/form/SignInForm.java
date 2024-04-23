package com.webspringmvc.form;

import org.hibernate.validator.constraints.NotBlank;

public class SignInForm {
	@NotBlank(message = "Email can not be empty.")
	private String username;

	@NotBlank(message = "Password can not be empty.")
	private String password;
	
	public SignInForm() {
	}

	public SignInForm(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
