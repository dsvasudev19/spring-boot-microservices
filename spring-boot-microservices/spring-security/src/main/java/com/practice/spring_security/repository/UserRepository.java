package com.practice.spring_security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.spring_security.models.UserPrinciple;

public interface UserRepository extends JpaRepository<UserPrinciple, Integer>{
	UserPrinciple findByUsername(String username);

}
