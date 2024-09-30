package com.practice.department_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
@Table(name = "department")
public class Department {
    @Id
    @Column(name = "dept_id")
    private long deptId;
    @Column(name = "dept_name")
    private String deptName;
}
