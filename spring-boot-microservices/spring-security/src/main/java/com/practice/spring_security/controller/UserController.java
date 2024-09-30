package com.practice.spring_security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.spring_security.models.UserPrinciple;
import com.practice.spring_security.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService userService;
	
	@GetMapping("/all")
	public ResponseEntity<?> getAllUsers(){
		return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getUserById(@PathVariable int id){
		UserPrinciple userFound=userService.getUserById(id);
		if(userFound!=null) {
			return new ResponseEntity<>(userFound,HttpStatus.OK);
		}
		return ResponseEntity.noContent().build();
	}

	@PostMapping("/add-new")
	public ResponseEntity<?> addNewUser(@RequestBody UserPrinciple newUser){
		return new ResponseEntity<>(userService.createUser(newUser),HttpStatus.OK);
	}
	
	@GetMapping("/user-name/{username}")
	public ResponseEntity<?> getUserByUserName(@PathVariable String username){
		return new ResponseEntity<>(userService.getUserByUsername(username),HttpStatus.OK);
	}
}
