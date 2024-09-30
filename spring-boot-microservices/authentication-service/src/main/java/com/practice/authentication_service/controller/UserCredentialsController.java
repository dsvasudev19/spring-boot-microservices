package com.practice.authentication_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.practice.authentication_service.entity.UserCredentials;
import com.practice.authentication_service.service.UserCredentialsService;

@RestController
@RequestMapping("/auth")
public class UserCredentialsController {

	@Autowired
	private UserCredentialsService userCredentialsService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/register")
	public UserCredentials register(@RequestBody UserCredentials user) {
		return userCredentialsService.register(user);
	}

	@GetMapping("/validate/token")
	public boolean validateToken(@RequestParam String token) {
		return userCredentialsService.verifyToken(token);
	}

	@PostMapping("/validate/user")
	public String getToken(@RequestBody UserCredentials user) {
		System.out.println("user : " + user);
		Authentication authenticate = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword()));
		System.out.println("authenticated?? : " + authenticate.isAuthenticated());
		if (authenticate.isAuthenticated()) {
			return userCredentialsService.generateToken(user.getName());
		}
		return null;
	}

}
