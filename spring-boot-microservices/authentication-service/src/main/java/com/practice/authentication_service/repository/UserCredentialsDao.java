package com.practice.authentication_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practice.authentication_service.entity.UserCredentials;

@Repository
public interface UserCredentialsDao extends JpaRepository<UserCredentials, Integer> {
	Optional<UserCredentials> findByName(String name);
}
