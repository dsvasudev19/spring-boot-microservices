package com.practice.employee_service.service;

import com.practice.employee_service.models.EmployeePojo;

import java.util.List;

public interface EmployeeService {
    List<EmployeePojo> getAllEmployees();
    EmployeePojo getEmployeeById(long id);
    EmployeePojo addNewEmployee(EmployeePojo newEmpPojo);
    EmployeePojo updateEmployee(EmployeePojo updatedEmpPojo);
    void deleteEmployeeById(long id);
    List<EmployeePojo> findAllEmployeesByDeptId(long id);
    EmployeePojo findByEmail(String email);
}
