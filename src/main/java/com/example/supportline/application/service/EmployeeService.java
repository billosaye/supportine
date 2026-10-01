package com.example.supportline.application.service;

import com.example.supportline.domain.model.Employee;
import com.example.supportline.domain.repository.EmployeeRepository;
import com.example.supportline.exception.EmployeeNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    //CreateEmployee Method
    @Transactional(readOnly = true)
    public Employee createEmployee(
            String name,
            String email,
            String department
    ) {
        String normalizedEmail = email.trim().toLowerCase();

        if (employeeRepository.existsByEmail(normalizedEmail)) {
            throw new IllegalArgumentException(
                    "Employee with this email already exists"
            );
        }

        Employee employee = new Employee(
                name,
                normalizedEmail,
                department
        );
        return employeeRepository.save(employee);
    }

    //getEmployee method
    @Transactional(readOnly = true)
    public Employee getEmployee(Long id) {

        return employeeRepository.findById(id)
                .orElseThrow(
                        () -> new EmployeeNotFoundException(id)
                );
    }

    //getAllEmployees
    @Transactional(readOnly = true)
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

}