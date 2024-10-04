package com.practice.spring_security.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.spring_security.models.UserPrinciple;
import com.practice.spring_security.respository.UserPrincipleRepository;

@Service
public class UserPrincipleService {
	@Autowired
	private UserPrincipleRepository userRepo;

	public List<UserPrinciple> getAllUsers() {
		return userRepo.findAll();
	}

	public UserPrinciple getUserById(int id) {
		return userRepo.findById(id).get();
	}

	public UserPrinciple getUserByUsername(String username) {
		return userRepo.findByUsername(username);
	}

	public UserPrinciple addNewUser(UserPrinciple newUser) {
		return userRepo.save(newUser);
	}

	public UserPrinciple updateUser(int id, UserPrinciple updatedUser) {
		Optional<UserPrinciple> userFound = userRepo.findById(id);
		if (userFound.isPresent()) {
			UserPrinciple userPrinciple = userFound.get();
			BeanUtils.copyProperties(updatedUser, userPrinciple);
			userRepo.save(userPrinciple);
			return updatedUser;
		}
		return null;
	}

	public boolean deleteUser(int id) {
		userRepo.deleteById(id);
		return true;
	}

}
