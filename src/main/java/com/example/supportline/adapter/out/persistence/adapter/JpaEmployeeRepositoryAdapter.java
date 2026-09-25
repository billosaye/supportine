package com.example.supportline.adapter.out.persistence.adapter;



import com.example.supportline.adapter.out.persistence.repository.SpringDataEmployeeRepository;
import com.example.supportline.domain.model.Employee;
import com.example.supportline.domain.repository.EmployeeRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaEmployeeRepositoryAdapter implements EmployeeRepository {

    private final SpringDataEmployeeRepository springDataEmployeeRepository;

    public JpaEmployeeRepositoryAdapter(
            SpringDataEmployeeRepository springDataEmployeeRepository
    ) {
        this.springDataEmployeeRepository = springDataEmployeeRepository;
    }

    @Override
    public Employee save(Employee employee) {
        return springDataEmployeeRepository.save(employee);
    }

    @Override
    public Optional<Employee> findById(Long id) {
        return springDataEmployeeRepository.findById(id);
    }

    @Override
    public List<Employee> findAll() {
        return springDataEmployeeRepository.findAll();
    }

    @Override
    public boolean existsByEmail(String email) {
        return springDataEmployeeRepository.existsByEmail(email);
    }
}