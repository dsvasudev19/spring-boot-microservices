package com.practice.authentication_service.service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.practice.authentication_service.entity.Role;
import com.practice.authentication_service.entity.UserCredentials;

public class CustomUserDetails implements UserDetails {

	private String name;
	private String password;
	List<Role> roles;

	public CustomUserDetails(UserCredentials user) {
		this.name = user.getName();
		this.password = user.getPassword();
//		this.roles=user.getRoles();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singleton(new SimpleGrantedAuthority("USER"));
//		return roles.stream().map((role)->new SimpleGrantedAuthority(role.getName())).toList();
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
