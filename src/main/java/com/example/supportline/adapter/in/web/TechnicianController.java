package com.example.supportline.adapter.in.web;

import com.example.supportline.adapter.in.web.dto.request.CreateTechnicianRequest;
import com.example.supportline.adapter.in.web.dto.response.TechnicianResponse;
import com.example.supportline.application.service.TechnicianService;
import com.example.supportline.domain.model.Technician;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/technicians")
public class TechnicianController {

    private final TechnicianService technicianService;

    public TechnicianController(TechnicianService technicianService) {
        this.technicianService = technicianService;
    }

    @PostMapping
    public ResponseEntity<TechnicianResponse> createTechnician(
            @RequestBody CreateTechnicianRequest request
    ) {

        Technician technician = technicianService.createTechnician(
                request.name(),
                request.email(),
                request.specialisation()
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(TechnicianResponse.from(technician));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TechnicianResponse> getTechnician(
            @PathVariable Long id
    ) {

        Technician technician = technicianService.getTechnician(id);

        return ResponseEntity.ok(
                TechnicianResponse.from(technician)
        );
    }

    @GetMapping
    public ResponseEntity<List<TechnicianResponse>> getAllTechnicians() {

        List<Technician> technicians = technicianService.getAllTechnicians();

        List<TechnicianResponse> response = technicians.stream()
                .map(TechnicianResponse::from)
                .toList();

        return ResponseEntity.ok(response);
    }
}