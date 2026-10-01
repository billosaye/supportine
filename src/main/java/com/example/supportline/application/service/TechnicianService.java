package com.example.supportline.application.service;

import com.example.supportline.domain.model.Technician;
import com.example.supportline.domain.repository.TechnicianRepository;
import com.example.supportline.exception.TechnicianNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TechnicianService {

    private final TechnicianRepository technicianRepository;

    public TechnicianService(
            TechnicianRepository technicianRepository
    ) {
        this.technicianRepository = technicianRepository;
    }

    public Technician createTechnician(
            String name,
            String email,
            String specialisation
    ) {

        String normalizedEmail = email.trim().toLowerCase();

        if (technicianRepository.existsByEmail(normalizedEmail)) {
            throw new IllegalArgumentException(
                    "Technician with this email already exists"
            );
        }

        Technician technician = new Technician(
                name,
                normalizedEmail,
                specialisation
        );

        return technicianRepository.save(technician);
    }

    @Transactional(readOnly = true)
    public Technician getTechnician(Long id) {

        return technicianRepository.findById(id)
                .orElseThrow(
                        () -> new TechnicianNotFoundException(id)
                );
    }

    @Transactional(readOnly = true)
    public List<Technician> getAllTechnicians() {

        return technicianRepository.findAll();
    }
}