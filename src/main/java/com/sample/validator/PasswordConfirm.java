package com.sample.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = PasswordConfirmValidator.class)
//@Target({ ElementType.METHOD, ElementType.FIELD })
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordConfirm {
	
    public String message() default "password and password confirm must be same.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String password();

    String passwordConfirm();

}