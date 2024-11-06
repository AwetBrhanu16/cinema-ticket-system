package com.example.cinema_ticket_system.repository;

import com.example.cinema_ticket_system.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepo extends JpaRepository<Movie, Long> {
    Movie findByMovieName(String movieName);

}