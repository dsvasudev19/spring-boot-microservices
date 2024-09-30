package com.practice.employee_service.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class EmployeePojo {
    private long employeeId;
    private String name;
    private long deptId;
    private long experience;
    private String email;
    private String phone;
}
