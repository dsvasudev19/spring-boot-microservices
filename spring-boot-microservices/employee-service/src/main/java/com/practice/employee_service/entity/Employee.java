package com.practice.employee_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "employee_id")
    private long employeeId;
    @Column(name = "deptId",nullable = false)
    private long deptId;
    @Column(name = "name")
    private String name;
    @Column(name = "email",nullable = false)
    private String email;
    @Column(name = "phone")
    private String phone;
    @Column(name = "experience")
    private long experience;
}
