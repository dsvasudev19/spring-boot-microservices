package com.practice.employee_service.repository;

import com.practice.employee_service.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    List<Employee> findAllByDeptId(long deptId);
    Employee findByEmail(String email);
}
