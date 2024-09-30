package com.practice.department_service.services;

import com.practice.department_service.model.DepartmentPojo;

import java.util.List;

public interface DepartmentService {
     List<DepartmentPojo> getAllDepartments();
     DepartmentPojo getDepartmentById(long deptId);
     DepartmentPojo createNewDepartment(DepartmentPojo newDeptDto);
     DepartmentPojo updateDepartment( DepartmentPojo updatedDept);
     void deleteDepartment(long deptId);
}
