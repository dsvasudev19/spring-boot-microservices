package com.practice.authentication_service.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.practice.authentication_service.entity.UserCredentials;
import com.practice.authentication_service.repository.UserCredentialsDao;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	UserCredentialsDao userCredentialsDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserCredentials> user = userCredentialsDao.findByName(username);
		System.out.println("user 2: " + user);
		return user.map(CustomUserDetails::new)
				.orElseThrow(() -> new UsernameNotFoundException("Username/password not valid!"));
	}
}