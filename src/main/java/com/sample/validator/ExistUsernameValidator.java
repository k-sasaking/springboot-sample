package com.sample.validator;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.sample.entity.User;
import com.sample.service.AccountService;

public class ExistUsernameValidator  implements ConstraintValidator<ExistUsername, String> {

	@Autowired
	AccountService accountService;
	
	@Override
    public void initialize(ExistUsername existUsername) {
		
	}

	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		Optional<User> user = accountService.getUser(value);
		
		if(user.isPresent())
			return false;
		
		return true;
	}
	
}
