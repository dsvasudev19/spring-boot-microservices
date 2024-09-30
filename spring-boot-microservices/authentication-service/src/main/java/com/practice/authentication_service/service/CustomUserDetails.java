package com.practice.authentication_service.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.practice.authentication_service.entity.UserCredentials;

public class CustomUserDetails implements UserDetails {

	private String name;
	private String password;

	public CustomUserDetails(UserCredentials user) {
		this.name = user.getName();
		this.password = user.getPassword();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return name;
	}
}
