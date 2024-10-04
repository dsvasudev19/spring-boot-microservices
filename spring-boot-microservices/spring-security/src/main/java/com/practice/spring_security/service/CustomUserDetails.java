package com.practice.spring_security.service;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.practice.spring_security.models.UserPrinciple;

public class CustomUserDetails implements UserDetails {

	
	UserPrinciple userPrinciple;
	
	public CustomUserDetails(UserPrinciple userPrinciple) {
		this.userPrinciple=userPrinciple;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return Collections.singleton(new SimpleGrantedAuthority("User"));
	}

	@Override
	public String getPassword() {

		return userPrinciple.getPassword();
	}

	@Override
	public String getUsername() {
		return userPrinciple.getUsername();
	}

}
