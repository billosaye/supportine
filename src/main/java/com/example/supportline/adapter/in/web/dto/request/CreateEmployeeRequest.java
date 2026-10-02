package com.example.supportline.adapter.in.web.dto.request;


import com.example.supportline.adapter.in.web.EmployeeController;
import com.example.supportline.application.service.EmployeeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employees")
public record CreateEmployeeRequest( String name, String email, String department) {
}
