package com.example.supportline.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "technicians")
@Getter
public class Technician {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String specialisation;

    // Required by JPA
    protected Technician() {
    }

    // Used by our application when creating a Technician
    public Technician(
            String name,
            String email,
            String specialisation
    ) {
        this.name = name;
        this.email = email;
        this.specialisation = specialisation;
    }
}