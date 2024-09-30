package com.practice.spring_security.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.practice.spring_security.models.UserPrinciple;
import com.practice.spring_security.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	PasswordEncoder encoder=new BCryptPasswordEncoder();
	
	public List<UserPrinciple> getAllUsers(){
		return userRepository.findAll();
	}
	
	public UserPrinciple getUserById(int id) {
		return userRepository.findById(id).get();
	}
	
	public UserPrinciple createUser(UserPrinciple user) {
		UserPrinciple newUser=new UserPrinciple();
		BeanUtils.copyProperties(user, newUser);
		newUser.setPassword(encoder.encode(user.getPassword()));
		return userRepository.save(newUser);
	}
	
	public boolean deleteUserById(int id) {
		userRepository.deleteById(id);
		return true;
	}

	public UserPrinciple getUserByUsername(String username) {
		UserPrinciple user=userRepository.findByUsername(username);
		return user;
	}

}
