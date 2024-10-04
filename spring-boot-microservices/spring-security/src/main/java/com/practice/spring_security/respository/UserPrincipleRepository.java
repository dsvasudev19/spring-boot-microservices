package com.practice.spring_security.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.spring_security.models.UserPrinciple;

public interface UserPrincipleRepository extends JpaRepository<UserPrinciple, Integer> {
	
	UserPrinciple findByUsername(String username);

}
