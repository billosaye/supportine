package com.example.supportline.adapter.in.web.dto.response;

import com.example.supportline.domain.model.Employee;

public record EmployeeResponse(Long id,
                               String name,
                               String email,
                               String department) {
    public static EmployeeResponse from(Employee employee) {
        return new EmployeeResponse(employee.getId(), employee.getName(), employee.getEmail(), employee.getDepartment());
    }
}
