package com.sample.service;

import java.util.Optional;

import com.sample.entity.User;

public interface AccountService {
	
    public Optional<User> getUser(String username);
    public void signupUser(User user);
    public void updateLastLogin(String username);

}
