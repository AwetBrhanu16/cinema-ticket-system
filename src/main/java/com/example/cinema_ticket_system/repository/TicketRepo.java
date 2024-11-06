package com.example.cinema_ticket_system.repository;

import com.example.cinema_ticket_system.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TicketRepo extends JpaRepository<Ticket, Integer> {

    Optional<Ticket> findByTicketId(Integer ticketId);


}
