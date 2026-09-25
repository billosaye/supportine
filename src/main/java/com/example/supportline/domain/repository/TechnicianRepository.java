package com.example.supportline.domain.repository;

import com.example.supportline.domain.model.Technician;

import java.util.List;
import java.util.Optional;

public interface TechnicianRepository {

    Technician save(Technician technician);

    Optional<Technician> findById(Long id);

    List<Technician> findAll();

    boolean existsByEmail(String email);
}
