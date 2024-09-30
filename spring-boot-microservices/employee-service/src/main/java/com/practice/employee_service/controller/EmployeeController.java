package com.practice.employee_service.controller;

import com.practice.employee_service.models.EmployeePojo;
import com.practice.employee_service.service.EmployeeService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService empService;
    
    public static final Logger LOG=LoggerFactory.getLogger(EmployeeController.class);

    @GetMapping
    public ResponseEntity<List<EmployeePojo>> getAllEmployees(){
    	LOG.info("Inside get all employees");
        List<EmployeePojo> employees=empService.getAllEmployees();
        return new ResponseEntity<>(employees, HttpStatusCode.valueOf(200));
    }
    @GetMapping("/{id}")
    public ResponseEntity<EmployeePojo> getEmployeeById(@PathVariable long id){
    	LOG.info("Inside get employee By id");
        EmployeePojo empPojo=empService.getEmployeeById(id);
        if(empPojo!=null){
            return new ResponseEntity<>(empPojo,HttpStatusCode.valueOf(200));
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> getEmployeeByEmail(@PathVariable String email){
    	LOG.info("Inside get employee by email");
        EmployeePojo empPojo=empService.findByEmail(email);
        if(empPojo!=null){
            return new ResponseEntity<>(empPojo,HttpStatusCode.valueOf(200));
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/employees-of-department/{deptId}")
    public ResponseEntity<List<EmployeePojo>> getEmployeesByDeptId(@PathVariable long deptId){
    	LOG.info("Inside get employees by department");
        List<EmployeePojo> employeePojos=empService.findAllEmployeesByDeptId(deptId);
        return new ResponseEntity<>(employeePojos,HttpStatusCode.valueOf(200));

    }

    @PostMapping
    public ResponseEntity<?> addNewEmployee(@RequestBody EmployeePojo newEmpPojo){
    	LOG.info("Inside add new employee");
        EmployeePojo addedEmployeePojo=empService.addNewEmployee(newEmpPojo);
        return new ResponseEntity<>(addedEmployeePojo,HttpStatusCode.valueOf(200));
    }
    @PutMapping
    public ResponseEntity<?> updateEmployeeDetails(@RequestBody EmployeePojo updatedEmployeePojo){
    	LOG.info("Inside update employee");
        EmployeePojo updatedPojo=empService.updateEmployee(updatedEmployeePojo);
        return new ResponseEntity<>(updatedPojo,HttpStatusCode.valueOf(200));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployeeById(@PathVariable long id){
        empService.deleteEmployeeById(id);
        LOG.info("Delete Employee By id");
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
    



    







}
