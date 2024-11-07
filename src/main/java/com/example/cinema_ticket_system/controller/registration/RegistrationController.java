package com.example.cinema_ticket_system.controller.registration;

import com.example.cinema_ticket_system.request.UserRequest;
import com.example.cinema_ticket_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

    @Autowired
    private UserService userService;

    @PostMapping("/addUser")
    public ResponseEntity<String> register(@RequestBody UserRequest userRequest) {
        String result = userService.registerUser(userRequest);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
}
