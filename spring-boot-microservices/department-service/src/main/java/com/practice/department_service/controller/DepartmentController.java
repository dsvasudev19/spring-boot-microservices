package com.practice.department_service.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import com.practice.department_service.model.DepartmentPojo;
import com.practice.department_service.model.EmployeePojo;
import com.practice.department_service.services.DepartmentService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/department")
public class DepartmentController {
	@Autowired
	private DepartmentService deptService;

	public static final Logger LOG = LoggerFactory.getLogger(DepartmentController.class);

	@GetMapping
	public ResponseEntity<List<DepartmentPojo>> getAllDepartments() {
		LOG.info("Inside get all departments");
		return new ResponseEntity<>(deptService.getAllDepartments(), HttpStatusCode.valueOf(200));
	}

	@GetMapping("/{deptId}")
	@CircuitBreaker(name="ciremp",fallbackMethod = "empFallBack")
	public ResponseEntity<DepartmentPojo> getDepartmentById(@PathVariable("deptId") long id) {
		LOG.info("Get department by id");
		DepartmentPojo departmentFound = deptService.getDepartmentById(id);
		if (departmentFound != null) {
			RestClient restClient = RestClient.create();
			@SuppressWarnings("unchecked")
			List<EmployeePojo> allEmployeesOfDepartment = restClient.get()
					.uri("http://localhost:8083/employee/employees-of-department/" + id).retrieve().body(List.class);
			departmentFound.setAllEmployees(allEmployeesOfDepartment);
			return new ResponseEntity<>(departmentFound, HttpStatusCode.valueOf(200));
		} else {
			return ResponseEntity.noContent().build();
		}
	}
	
	public DepartmentPojo empFallBack() {
		return new DepartmentPojo(0,"fallback",null);
	}

	@PostMapping
	public ResponseEntity<DepartmentPojo> createNewDepartment(@RequestBody DepartmentPojo newDepartmentPojo) {
		LOG.info("Create new department");
		DepartmentPojo newDepartment = deptService.createNewDepartment(newDepartmentPojo);
		return new ResponseEntity<>(newDepartment, HttpStatusCode.valueOf(200));
	}

	@PutMapping
	public ResponseEntity<?> updateDepartment(@RequestBody DepartmentPojo updatedDepartmentPojo) {
		LOG.info("Update department");
		DepartmentPojo updatedPojo = deptService.updateDepartment(updatedDepartmentPojo);
		return new ResponseEntity<>(updatedPojo, HttpStatusCode.valueOf(200));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteDeparment(@PathVariable long id) {
		LOG.info("Delete department by id");
		deptService.deleteDepartment(id);
		return new ResponseEntity<>(true, HttpStatusCode.valueOf(200));
	}

}
