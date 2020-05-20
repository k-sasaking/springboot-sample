package com.sample.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.sample.service.AccountService;

@Configuration
public class LoginSuccessHandler  implements AuthenticationSuccessHandler {

	@Autowired
	AccountService accountService;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication auth) throws IOException, ServletException {
		
		accountService.updateLastLogin((String)auth.getName());
		
        response.sendRedirect("/admin?loginSuccess");		
	}

}
