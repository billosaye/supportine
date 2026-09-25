package com.example.supportline.domain.repository;

import com.example.supportline.domain.model.Ticket;

import java.util.List;
import java.util.Optional;

public interface TicketRepository {
    Ticket save(Ticket ticket);

    Optional<Ticket> findById(Long id);

    List<Ticket> findAll();

    void deleteById(Long id);

    boolean existsById(Long id);

}
