package com.sample.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sample.form.SignupForm;
import com.sample.service.AccountService;


@Controller
public class AccountController {

	@Autowired
	AccountService accountService;
	
	@RequestMapping(value = "/signin", method = RequestMethod.GET)
	public String signin(
			Model model,
			@RequestParam(name = "username", required = false)
			String username,
			@RequestParam
			Optional<String> error
			) {
		
		if(error.isPresent()) {
	    	model.addAttribute("messageType", "danger"); 	
	    	model.addAttribute("message", "Cannot Authentication...Please check username and password"); 	
		}
    	model.addAttribute("username", username); 	
	    	
        return "account/signin";
	}
		
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String singup(
			Model model,
    		@ModelAttribute("signupForm")
    		SignupForm signupForm
    	) {
    	
    	model.addAttribute("signupForm", signupForm); 	
	    	
        return "account/signup";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String doSingup(
			Model model,
    		@ModelAttribute("signupForm")
    		@Validated
    		SignupForm signupForm,
    		BindingResult result
    	) {
		
    	if(result.hasErrors()) {
	    	model.addAttribute("messageType", "danger"); 	
	    	model.addAttribute("message", "Please check error message"); 	
        	model.addAttribute("postForm", signupForm); 	
            return "account/signup";
    	}
    	
    	accountService.signupUser(signupForm.getUser());
        return "redirect:/signin?username=" + signupForm.getUser().getUsername();
	}

}
