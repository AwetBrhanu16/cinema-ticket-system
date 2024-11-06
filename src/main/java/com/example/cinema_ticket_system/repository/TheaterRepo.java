package com.example.cinema_ticket_system.repository;

import com.example.cinema_ticket_system.entities.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TheaterRepo extends JpaRepository<Theater, Integer> {

    Optional<Theater> findByTheaterId(Integer theaterId);

    Theater findByAddress(String address);

}
