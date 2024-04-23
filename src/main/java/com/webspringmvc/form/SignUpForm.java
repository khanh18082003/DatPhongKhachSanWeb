package com.webspringmvc.form;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class SignUpForm {
	@NotBlank(message = "Email can not be empty.")
	private String username;

	@NotBlank(message = "Password can not be empty.")
	@Size(min = 8, message = "Length of password must be greater than 8.")
	private String password;

	@NotBlank(message = "Confirm password can not be empty.")
	private String confirmPassword;
	
	public SignUpForm() {
		
	}

	public SignUpForm(String username, String password, String confirmPassword) {
		this.username = username;
		this.password = password;
		this.confirmPassword = confirmPassword;
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

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	public boolean checkConfirmPassword() {
		return confirmPassword.equals(password);
	}
}
