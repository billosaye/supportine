package com.example.supportline.adapter.in.web;

import com.example.supportline.adapter.in.web.dto.request.CreateEmployeeRequest;
import com.example.supportline.adapter.in.web.dto.response.EmployeeResponse;
import com.example.supportline.application.service.EmployeeService;
import com.example.supportline.domain.model.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @PostMapping
    public ResponseEntity<EmployeeResponse> createEmployee(@RequestBody CreateEmployeeRequest request) {
        Employee employee = employeeService.createEmployee(
                request.name(),
                request.email(),
                request.department()
        );
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(EmployeeResponse.from(employee));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getEmployee(
            @PathVariable Long id
    ) {

        Employee employee = employeeService.getEmployee(id);

        return ResponseEntity.ok(
                EmployeeResponse.from(employee)
        );
    }
    @GetMapping
    public ResponseEntity<List<EmployeeResponse>> getAllEmployees() {

        List<Employee> employees = employeeService.getAllEmployees();

        List<EmployeeResponse> response = employees.stream()
                .map(EmployeeResponse::from)
                .toList();

        return ResponseEntity.ok(response);
    }

}



