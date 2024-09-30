package com.practice.employee_service.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class DepartmentDto {
    private long deptId;
    private String deptName;
}
