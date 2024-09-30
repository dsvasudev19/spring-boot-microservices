package com.practice.department_service.services;

import com.practice.department_service.entity.Department;
import com.practice.department_service.model.DepartmentPojo;
import com.practice.department_service.repository.DepartmentRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepo;

    @Override
    public List<DepartmentPojo> getAllDepartments() {
        List<Department> departments = departmentRepo.findAll();
        List<DepartmentPojo> departmentPojos = new ArrayList<>();
        departments.stream().forEach((department -> {
            DepartmentPojo dto = new DepartmentPojo();
            BeanUtils.copyProperties(department, dto);
            departmentPojos.add(dto);
        }));
        return departmentPojos;
    }

    @Override
    public DepartmentPojo getDepartmentById(long deptId) {

        Optional<Department> department = departmentRepo.findById(deptId);
        if (department.isPresent()) {
            DepartmentPojo dto = new DepartmentPojo();
            BeanUtils.copyProperties(department.get(), dto);
            return dto;
        }
        return null;
    }

    @Override
    public DepartmentPojo createNewDepartment(DepartmentPojo newDeptDto) {
        Department department = new Department();
        BeanUtils.copyProperties(newDeptDto, department);
        departmentRepo.save(department);
        return newDeptDto;
    }

    @Override
    public DepartmentPojo updateDepartment( DepartmentPojo updatedDept) {
        Department department = new Department();
        BeanUtils.copyProperties(updatedDept, department);
        departmentRepo.saveAndFlush(department);
        return updatedDept;
    }

    @Override
    public void deleteDepartment(long deptId) {
        departmentRepo.deleteById(deptId);
    }
}
