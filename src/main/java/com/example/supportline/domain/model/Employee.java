package com.example.supportline.domain.model;


import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "employees")

public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String department;

    protected Employee() {}

    public Employee(String name, String email, String department) {}
}
