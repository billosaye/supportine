package com.example.supportline.adapter.out.persistence.adapter;


import com.example.supportline.adapter.out.persistence.repository.SpringDataTechnicianRepository;
import com.example.supportline.domain.model.Technician;
import com.example.supportline.domain.repository.TechnicianRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaTechnicianRepositoryAdapter implements TechnicianRepository {

    private final SpringDataTechnicianRepository springDataTechnicianRepository;

    public JpaTechnicianRepositoryAdapter(
            SpringDataTechnicianRepository springDataTechnicianRepository
    ) {
        this.springDataTechnicianRepository = springDataTechnicianRepository;
    }

    @Override
    public Technician save(Technician technician) {
        return springDataTechnicianRepository.save(technician);
    }

    @Override
    public Optional<Technician> findById(Long id) {
        return springDataTechnicianRepository.findById(id);
    }

    @Override
    public List<Technician> findAll() {
        return springDataTechnicianRepository.findAll();
    }

    @Override
    public boolean existsByEmail(String email) {
        return springDataTechnicianRepository.existsByEmail(email);
    }
}
