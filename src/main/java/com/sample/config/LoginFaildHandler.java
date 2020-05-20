package com.sample.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;


@Configuration
public class LoginFaildHandler implements AuthenticationFailureHandler {
	

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		String usernameParameter = request.getParameter("username");
		if(!"".equals(usernameParameter)) {
			usernameParameter = "&username=" + usernameParameter; 
		}

		response.sendRedirect("/signin?error" + usernameParameter);

	}
	
}
