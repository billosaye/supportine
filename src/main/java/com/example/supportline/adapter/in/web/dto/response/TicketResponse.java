package com.example.supportline.adapter.in.web.dto.response;

import com.example.supportline.domain.enums.TicketCategory;
import com.example.supportline.domain.enums.TicketPriority;
import com.example.supportline.domain.enums.TicketStatus;
import com.example.supportline.domain.model.Ticket;

import java.time.LocalDateTime;

public record TicketResponse(
        Long id,
        String title,
        String description,
        TicketCategory category,
        TicketPriority priority,
        TicketStatus status,
        Long employeeId,
        Long technicianId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

    public static TicketResponse from(Ticket ticket) {

        Long technicianId = null;

        if (ticket.getAssignedTechnician() != null) {
            technicianId = ticket.getAssignedTechnician().getId();
        }

        return new TicketResponse(
                ticket.getId(),
                ticket.getTitle(),
                ticket.getDescription(),
                ticket.getCategory(),
                ticket.getPriority(),
                ticket.getStatus(),
                ticket.getReportedBy().getId(),
                technicianId,
                ticket.getCreatedAt(),
                ticket.getUpdatedAt()
        );
    }
}