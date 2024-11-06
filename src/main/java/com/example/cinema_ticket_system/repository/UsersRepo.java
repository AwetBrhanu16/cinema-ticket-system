package com.example.cinema_ticket_system.repository;

import com.example.cinema_ticket_system.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepo extends JpaRepository<User, Integer> {
    Optional<User> findByEmailId(String emailId);

    Optional<User> findByUserName(String userName);
}


