package com.example.supportline.domain.model;

import com.example.supportline.domain.enums.TicketCategory;
import com.example.supportline.domain.enums.TicketPriority;
import com.example.supportline.domain.enums.TicketStatus;


import com.example.supportline.domain.exception.InvalidTicketStateException;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Table(name = "tickets")
@Getter
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private TicketCategory category;

    private TicketPriority priority;

    private TicketStatus status;

    @ManyToOne
    @JoinColumn(name = "reported_by_id")
    private Employee reportedBy;

    @ManyToOne
    @JoinColumn(name = "assigned_technician_id")
    private Technician assignedTechnician;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


    // Required by JPA
    protected Ticket() {
    }


    // 3.1 Ticket creation rules
    public Ticket(
            String title,
            String description,
            TicketCategory category,
            TicketPriority priority,
            Employee reportedBy
    ) {

        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException(
                    "Ticket title cannot be empty"
            );
        }

        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException(
                    "Ticket description cannot be empty"
            );
        }

        if (category == null) {
            throw new IllegalArgumentException(
                    "Ticket category cannot be null"
            );
        }

        if (priority == null) {
            throw new IllegalArgumentException(
                    "Ticket priority cannot be null"
            );
        }

        if (reportedBy == null) {
            throw new IllegalArgumentException(
                    "Reporting employee cannot be null"
            );
        }

        this.title = title;
        this.description = description;
        this.category = category;
        this.priority = priority;
        this.reportedBy = reportedBy;

        this.status = TicketStatus.OPEN;

        LocalDateTime now = LocalDateTime.now();

        this.createdAt = now;
        this.updatedAt = now;
    }


    // 3.2 Assign technician
    public void assignTechnician(Technician technician) {

        if (technician == null) {
            throw new IllegalArgumentException(
                    "Technician cannot be null"
            );
        }

        if (this.status != TicketStatus.OPEN) {
            throw new InvalidTicketStateException(
                    "Technician can only be assigned to an OPEN ticket"
            );
        }

        this.assignedTechnician = technician;
        this.status = TicketStatus.IN_PROGRESS;
        this.updatedAt = LocalDateTime.now();
    }


    // 3.3 Resolve ticket
    public void resolve() {

        if (this.status != TicketStatus.IN_PROGRESS) {
            throw new InvalidTicketStateException(
                    "Only an IN_PROGRESS ticket can be resolved"
            );
        }

        this.status = TicketStatus.RESOLVED;
        this.updatedAt = LocalDateTime.now();
    }


    // 3.4 Close ticket
    public void close() {

        if (this.status != TicketStatus.RESOLVED) {
            throw new InvalidTicketStateException(
                    "Only a RESOLVED ticket can be closed"
            );
        }

        this.status = TicketStatus.CLOSED;
        this.updatedAt = LocalDateTime.now();
    }


    // 3.5 Update ticket details
    public void updateDetails(
            String title,
            String description,
            TicketCategory category,
            TicketPriority priority
    ) {

        if (this.status == TicketStatus.CLOSED) {
            throw new InvalidTicketStateException(
                    "A CLOSED ticket cannot be updated"
            );
        }

        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException(
                    "Ticket title cannot be empty"
            );
        }

        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException(
                    "Ticket description cannot be empty"
            );
        }

        if (category == null) {
            throw new IllegalArgumentException(
                    "Ticket category cannot be null"
            );
        }

        if (priority == null) {
            throw new IllegalArgumentException(
                    "Ticket priority cannot be null"
            );
        }

        this.title = title;
        this.description = description;
        this.category = category;
        this.priority = priority;

        this.updatedAt = LocalDateTime.now();
    }
}