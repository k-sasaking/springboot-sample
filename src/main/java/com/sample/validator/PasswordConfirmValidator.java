package com.sample.validator;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordConfirmValidator  implements ConstraintValidator<PasswordConfirm, Object> {
	
	private String message;
	private String passwordFieldName;
	private String passwordConfirmFieldName;

	@Override
    public void initialize(PasswordConfirm passwordConfirm) {
		message = passwordConfirm.message();
		passwordFieldName = passwordConfirm.password();
		passwordConfirmFieldName = passwordConfirm.passwordConfirm();
    }
    
    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
    	BeanWrapper beanWrapper = new BeanWrapperImpl(obj);
    	
    	Object passwordFiledValue = beanWrapper.getPropertyValue(passwordFieldName);
    	Object passwordConfirmFiledValue = beanWrapper.getPropertyValue(passwordConfirmFieldName);
    	
    	String password = (String)passwordFiledValue;
    	String passwordConfirm = (String)passwordConfirmFiledValue;
                
        if(password.equals(passwordConfirm))
        	return true;
        
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message)
                .addPropertyNode(passwordConfirmFieldName).addConstraintViolation();
        return false;
    }


}