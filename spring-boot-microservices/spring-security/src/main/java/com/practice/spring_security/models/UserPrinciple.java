package com.practice.spring_security.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
public class UserPrinciple {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cred_id")
	private int id;
	private String username;
	private String password;
	private String name;

	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "credential_roles",
        joinColumns = @JoinColumn(name = "cred_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
	List<Role> roles;

}
