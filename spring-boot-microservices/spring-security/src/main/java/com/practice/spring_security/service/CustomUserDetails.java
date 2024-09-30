package com.practice.spring_security.service;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.practice.spring_security.models.UserPrinciple;

public class CustomUserDetails implements UserDetails {

	private UserPrinciple userFound;

	public CustomUserDetails() {

	}

	public CustomUserDetails(UserPrinciple user) {
		this.userFound = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return userFound.getRoles().stream().map((role)->new SimpleGrantedAuthority(role.getName())).toList();
//		return userFound.getRoles().stream().map((role)-> new SimpleGrantedAuthority(role.getName())).toList();
//		return Collections.singleton(new SimpleGrantedAuthority("user"));
	}

	@Override
	public String getPassword() {

		return userFound.getPassword();
	}

	@Override
	public String getUsername() {

		return userFound.getUsername();
	}

}
