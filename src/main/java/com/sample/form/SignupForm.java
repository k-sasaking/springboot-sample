package com.sample.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.sample.entity.User;
import com.sample.validator.ExistUsername;
import com.sample.validator.PasswordConfirm;

@PasswordConfirm(password = "password", passwordConfirm = "passwordConfirm")
public class SignupForm {

	@ExistUsername
	@Email
	private String username;
	
	@Size(min=6,max=16, message="パスワードは{min}文字以上{max}文字以下です。")
	@Pattern(regexp="[a-zA-Z0-9]*", message="パスワードは英数である必要があります。")
    @NotBlank
    private String password;
		
	@NotBlank
	private String passwordConfirm;
	
	public SignupForm() {
	}
	
	public User getUser() {
		return new User(this);
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
	
	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}
	
}
