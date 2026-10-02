package com.example.supportline.adapter.in.web.dto.request;

public record CreateTechnicianRequest(
        String name,
        String email,
        String specialisation
) {
}
