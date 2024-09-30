package com.practice.spring_security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.practice.spring_security.models.UserPrinciple;
import com.practice.spring_security.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserPrinciple userFound = userRepository.findByUsername(username);
		if (userFound != null) {
			return new CustomUserDetails(userFound);
		}
		throw new UsernameNotFoundException("User Not found");
	}

}
