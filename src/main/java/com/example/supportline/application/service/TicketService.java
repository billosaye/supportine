package com.example.supportline.application.service;

import com.example.supportline.domain.enums.TicketCategory;
import com.example.supportline.domain.enums.TicketPriority;
import com.example.supportline.domain.model.Employee;
import com.example.supportline.domain.model.Technician;
import com.example.supportline.domain.model.Ticket;
import com.example.supportline.domain.repository.EmployeeRepository;
import com.example.supportline.domain.repository.TechnicianRepository;
import com.example.supportline.domain.repository.TicketRepository;
import com.example.supportline.exception.EmployeeNotFoundException;
import com.example.supportline.exception.TechnicianNotFoundException;
import com.example.supportline.exception.TicketNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TicketService {

    private final TicketRepository ticketRepository;
    private final EmployeeRepository employeeRepository;
    private final TechnicianRepository technicianRepository;

    public TicketService(
            TicketRepository ticketRepository,
            EmployeeRepository employeeRepository,
            TechnicianRepository technicianRepository
    ) {
        this.ticketRepository = ticketRepository;
        this.employeeRepository = employeeRepository;
        this.technicianRepository = technicianRepository;
    }

    public Ticket createTicket(
            String title,
            String description,
            TicketCategory category,
            TicketPriority priority,
            Long employeeId
    ) {

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(
                        () -> new EmployeeNotFoundException(employeeId)
                );

        Ticket ticket = new Ticket(
                title,
                description,
                category,
                priority,
                employee
        );

        return ticketRepository.save(ticket);
    }

    @Transactional(readOnly = true)
    public Ticket getTicket(Long id) {

        return ticketRepository.findById(id)
                .orElseThrow(
                        () -> new TicketNotFoundException(id)
                );
    }

    @Transactional(readOnly = true)
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public Ticket updateTicket(
            Long ticketId,
            String title,
            String description,
            TicketCategory category,
            TicketPriority priority
    ) {

        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(
                        () -> new TicketNotFoundException(ticketId)
                );

        ticket.updateDetails(
                title,
                description,
                category,
                priority
        );

        return ticketRepository.save(ticket);
    }

    public Ticket assignTechnician(
            Long ticketId,
            Long technicianId
    ) {

        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(
                        () -> new TicketNotFoundException(ticketId)
                );

        Technician technician =
                technicianRepository.findById(technicianId)
                        .orElseThrow(
                                () -> new TechnicianNotFoundException(technicianId)
                        );

        ticket.assignTechnician(technician);

        return ticketRepository.save(ticket);
    }

    public Ticket resolveTicket(Long ticketId) {

        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(
                        () -> new TicketNotFoundException(ticketId)
                );

        ticket.resolve();

        return ticketRepository.save(ticket);
    }

    public Ticket closeTicket(Long ticketId) {

        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(
                        () -> new TicketNotFoundException(ticketId)
                );

        ticket.close();

        return ticketRepository.save(ticket);
    }

    public void deleteTicket(Long ticketId) {

        if (!ticketRepository.existsById(ticketId)) {
            throw new TicketNotFoundException(ticketId);
        }

        ticketRepository.deleteById(ticketId);
    }
}