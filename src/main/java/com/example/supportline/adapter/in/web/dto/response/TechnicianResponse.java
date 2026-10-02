package com.example.supportline.adapter.in.web.dto.response;

import com.example.supportline.domain.model.Technician;

public record TechnicianResponse(
        Long id,
        String name,
        String email,
        String specialisation
) {

    public static TechnicianResponse from(Technician technician) {
        return new TechnicianResponse(
                technician.getId(),
                technician.getName(),
                technician.getEmail(),
                technician.getSpecialisation()
        );
    }
}