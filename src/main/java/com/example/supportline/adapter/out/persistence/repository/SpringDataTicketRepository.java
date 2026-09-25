package com.example.supportline.adapter.out.persistence.repository;


import com.example.supportline.domain.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataTicketRepository extends JpaRepository<Ticket, Long> {
}
