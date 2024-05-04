package com.webspringmvc.form;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class ResetPasswordForm {
	@NotBlank(message = "Password can not be empty.")
	@Size(min = 8, message = "Length of password must be greater than 8.")
	private String password;

	@NotBlank(message = "Confirm password can not be empty.")
	private String confirmPassword;

	public ResetPasswordForm() {
	}

	public ResetPasswordForm(String password, String confirmPassword) {
		this.password = password;
		this.confirmPassword = confirmPassword;
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
