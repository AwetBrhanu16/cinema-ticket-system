package com.example.cinema_ticket_system.controller;

import com.example.cinema_ticket_system.entities.User;
import com.example.cinema_ticket_system.request.UserLoginRequest;
import com.example.cinema_ticket_system.request.UserRequest;
import com.example.cinema_ticket_system.response.AuthResponse;
import com.example.cinema_ticket_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class UsersController {


    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/addUser")
    public ResponseEntity<String> addUser(@RequestBody UserRequest userRequest) {
        userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        String result = userService.addUser(userRequest);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody UserLoginRequest userLoginRequest) {
        return userService.verifyUser(userLoginRequest);
    }

    //    @PreAuthorize("hasRole('USER')")
    @GetMapping("/get")
    public List<UserRequest> getUser() {
        return userService.getAllUsers();
    }

}