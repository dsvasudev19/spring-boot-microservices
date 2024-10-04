package com.practice.spring_security.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
public class Role {
	@Id
	@Column(name = "role_id")
	private int roleId;
	private String name;
	
	@ManyToMany(mappedBy = "roles")
	List<UserPrinciple> users;

}
