package com.sample.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sample.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

	Optional<User> findById(String username);
	
}
