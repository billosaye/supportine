package com.example.supportline.adapter.out.persistence.repository;

import com.example.supportline.domain.model.Technician;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataTechnicianRepository extends JpaRepository<Technician, Long> {
    boolean existsByEmail(String email);
}
