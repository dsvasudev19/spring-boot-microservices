package com.practice.employee_service.service;

import com.practice.employee_service.entity.Employee;
import com.practice.employee_service.models.EmployeePojo;
import com.practice.employee_service.repository.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    private EmployeeRepository employeeRepo;
    @Override
    public List<EmployeePojo> getAllEmployees() {
        List<Employee> employeesFound=employeeRepo.findAll();
        List<EmployeePojo> employees=new ArrayList<>();
        employeesFound.stream().forEach((employee)->{
            EmployeePojo empPojo=new EmployeePojo();
            BeanUtils.copyProperties(employee,empPojo);
            employees.add(empPojo);
        });

        return employees;
    }

    @Override
    public EmployeePojo getEmployeeById(long id) {
        Optional<Employee> employeeFound=employeeRepo.findById(id);
        if(employeeFound.isPresent()){
            EmployeePojo empPojo=new EmployeePojo();
            BeanUtils.copyProperties(employeeFound.get(),empPojo);
            return empPojo;
        }
        return null;
    }

    @Override
    public EmployeePojo addNewEmployee(EmployeePojo newEmpPojo) {
        Employee emp=new Employee();
        BeanUtils.copyProperties(newEmpPojo,emp);
        employeeRepo.save(emp);
        return newEmpPojo;
    }

    @Override
    public EmployeePojo updateEmployee(EmployeePojo updatedEmpPojo) {
        Employee emp=new Employee();
        BeanUtils.copyProperties(updatedEmpPojo,emp);
        employeeRepo.saveAndFlush(emp);
        return updatedEmpPojo;
    }

    @Override
    public void deleteEmployeeById(long id) {
        employeeRepo.deleteById(id);
    }

    @Override
    public List<EmployeePojo> findAllEmployeesByDeptId(long id) {
        List<Employee> employeesFound=employeeRepo.findAllByDeptId(id);
        List<EmployeePojo> employees=new ArrayList<>();
        employeesFound.stream().forEach((employee)->{
            EmployeePojo empPojo=new EmployeePojo();
            BeanUtils.copyProperties(employee,empPojo);
            employees.add(empPojo);
        });
        return employees;
    }

    @Override
    public EmployeePojo findByEmail(String email) {
        Employee empFound=employeeRepo.findByEmail(email);
        if(empFound!=null){
            EmployeePojo empPojo=new EmployeePojo();
            BeanUtils.copyProperties(empFound,empPojo);
            return empPojo;
        }
        return null;
    }
}
