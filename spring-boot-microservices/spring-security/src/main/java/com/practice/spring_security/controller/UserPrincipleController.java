package com.practice.spring_security.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.spring_security.models.UserPrinciple;
import com.practice.spring_security.service.UserPrincipleService;

@RestController
@RequestMapping("/users")
public class UserPrincipleController {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserPrincipleService userService;

	@GetMapping
	public ResponseEntity<?> getAllUsers() {
		return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getUserById(@PathVariable int id) {
		UserPrinciple user = userService.getUserById(id);
		if (user != null) {
			return new ResponseEntity<>(user, HttpStatus.OK);
		}
		return ResponseEntity.noContent().build();
	}

	@PostMapping
	public ResponseEntity<?> addNewUser(@RequestBody UserPrinciple newUser) {
		UserPrinciple requestBody = new UserPrinciple();
		BeanUtils.copyProperties(newUser, requestBody);
		requestBody.setPassword(passwordEncoder.encode(newUser.getPassword()));
		UserPrinciple userCreated = userService.addNewUser(requestBody);
		return new ResponseEntity<>(userCreated, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateUser(@PathVariable int id, @RequestBody UserPrinciple updatedUser) {
		UserPrinciple requestBody = new UserPrinciple();
		BeanUtils.copyProperties(updatedUser, requestBody);

		UserPrinciple userUpdated = userService.updateUser(id, updatedUser);
		return new ResponseEntity<>(userUpdated, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable int id) {
		userService.deleteUser(id);
		return new ResponseEntity<>(true, HttpStatus.OK);
	}

	@GetMapping("/user-name/{username}")
	public ResponseEntity<?> getUserByUserName(@PathVariable String username) {
		UserPrinciple user = userService.getUserByUsername(username);
		if (user != null) {
			return new ResponseEntity<>(user, HttpStatus.OK);
		}
		return ResponseEntity.noContent().build();
	}

}
