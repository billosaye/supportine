package com.example.supportline.adapter.out.persistence.adapter;
import com.example.supportline.adapter.out.persistence.repository.SpringDataTicketRepository;
import com.example.supportline.domain.model.Ticket;
import com.example.supportline.domain.repository.TicketRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaTicketRepositoryAdapter implements TicketRepository {

    private final SpringDataTicketRepository springDataTicketRepository;

    public JpaTicketRepositoryAdapter(
            SpringDataTicketRepository springDataTicketRepository
    ) {
        this.springDataTicketRepository = springDataTicketRepository;
    }

    @Override
    public Ticket save(Ticket ticket) {
        return springDataTicketRepository.save(ticket);
    }

    @Override
    public Optional<Ticket> findById(Long id) {
        return springDataTicketRepository.findById(id);
    }

    @Override
    public List<Ticket> findAll() {
        return springDataTicketRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        springDataTicketRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return springDataTicketRepository.existsById(id);
    }
}