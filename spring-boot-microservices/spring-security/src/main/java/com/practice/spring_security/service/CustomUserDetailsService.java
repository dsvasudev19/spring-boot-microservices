package com.practice.spring_security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.practice.spring_security.models.UserPrinciple;
import com.practice.spring_security.respository.UserPrincipleRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	@Autowired
	private UserPrincipleRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserPrinciple userPrinciple=userRepo.findByUsername(username);
		if(userPrinciple!=null) {
			return new CustomUserDetails(userPrinciple);
		}
		throw new UsernameNotFoundException("User not found");
	}
	

}
