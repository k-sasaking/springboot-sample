package com.sample.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sample.entity.User;
import com.sample.entity.UserDetailsImpl;
import com.sample.repository.UserRepository;

@Service
public class AccountServiceImpl implements AccountService, UserDetailsService {

	@Autowired
	UserRepository userRepository;
		
	@Override
	public Optional<User> getUser(String username) {
		return userRepository.findById(username);
	}

	@Override
	public void signupUser(User user) {
		
		String encodedPassword = passwordEncoder().encode(user.getPassword());
		user.setPassword(encodedPassword);
		userRepository.saveAndFlush(user);
	}


	@Override
	public void updateLastLogin(String username) {
		Optional<User> user = userRepository.findById(username);
		user.get().setLastLogin();
		userRepository.saveAndFlush(user.get());
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<User> user = userRepository.findById(username);
		if(user.isPresent()){
			
			return new UserDetailsImpl(user.get(), getAuthorities(user.get()));
		} else {
			
			throw new UsernameNotFoundException("user not found.");
		}
		
	}
	
	private Collection<GrantedAuthority> getAuthorities(User user) {
		if(user.getIsAdmin())
			return AuthorityUtils.createAuthorityList("ROLE_USER", "ROLE_ADMIN");
		else
			return AuthorityUtils.createAuthorityList("ROLE_USER");
    }
	
    /**
     * パスワードをBCryptでエンコードする設定
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
}
