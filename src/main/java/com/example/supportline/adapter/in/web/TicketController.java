package com.example.supportline.adapter.in.web;

import com.example.supportline.adapter.in.web.dto.request.AssignTechnicianRequest;
import com.example.supportline.adapter.in.web.dto.request.CreateTicketRequest;
import com.example.supportline.adapter.in.web.dto.request.UpdateTicketRequest;
import com.example.supportline.adapter.in.web.dto.response.TicketResponse;
import com.example.supportline.application.service.TicketService;
import com.example.supportline.domain.model.Ticket;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    // CREATE TICKET
    @PostMapping
    public ResponseEntity<TicketResponse> createTicket(
            @RequestBody CreateTicketRequest request
    ) {

        Ticket ticket = ticketService.createTicket(
                request.title(),
                request.description(),
                request.category(),
                request.priority(),
                request.employeeId()
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(TicketResponse.from(ticket));
    }

    // GET ONE TICKET
    @GetMapping("/{id}")
    public ResponseEntity<TicketResponse> getTicket(
            @PathVariable Long id
    ) {

        Ticket ticket = ticketService.getTicket(id);

        return ResponseEntity.ok(
                TicketResponse.from(ticket)
        );
    }

    // GET ALL TICKETS
    @GetMapping
    public ResponseEntity<List<TicketResponse>> getAllTickets() {

        List<Ticket> tickets = ticketService.getAllTickets();

        List<TicketResponse> response = tickets.stream()
                .map(TicketResponse::from)
                .toList();

        return ResponseEntity.ok(response);
    }

    // UPDATE TICKET DETAILS
    @PutMapping("/{id}")
    public ResponseEntity<TicketResponse> updateTicket(
            @PathVariable Long id,
            @RequestBody UpdateTicketRequest request
    ) {

        Ticket ticket = ticketService.updateTicket(
                id,
                request.title(),
                request.description(),
                request.category(),
                request.priority()
        );

        return ResponseEntity.ok(
                TicketResponse.from(ticket)
        );
    }

    // ASSIGN TECHNICIAN
    @PutMapping("/{id}/assign")
    public ResponseEntity<TicketResponse> assignTechnician(
            @PathVariable Long id,
            @RequestBody AssignTechnicianRequest request
    ) {

        Ticket ticket = ticketService.assignTechnician(
                id,
                request.technicianId()
        );

        return ResponseEntity.ok(
                TicketResponse.from(ticket)
        );
    }

    // RESOLVE TICKET
    @PutMapping("/{id}/resolve")
    public ResponseEntity<TicketResponse> resolveTicket(
            @PathVariable Long id
    ) {

        Ticket ticket = ticketService.resolveTicket(id);

        return ResponseEntity.ok(
                TicketResponse.from(ticket)
        );
    }

    // CLOSE TICKET
    @PutMapping("/{id}/close")
    public ResponseEntity<TicketResponse> closeTicket(
            @PathVariable Long id
    ) {

        Ticket ticket = ticketService.closeTicket(id);

        return ResponseEntity.ok(
                TicketResponse.from(ticket)
        );
    }

    // DELETE TICKET
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicket(
            @PathVariable Long id
    ) {

        ticketService.deleteTicket(id);

        return ResponseEntity.noContent().build();
    }
}