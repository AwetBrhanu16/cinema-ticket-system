package com.example.cinema_ticket_system.controller;

import com.example.cinema_ticket_system.entities.Theater;
import com.example.cinema_ticket_system.request.TheaterRequest;
import com.example.cinema_ticket_system.request.TheaterSeatRequest;
import com.example.cinema_ticket_system.service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TheaterController {

    @Autowired
    private TheaterService theaterService;

    @PostMapping("/addTheater")
    public ResponseEntity<String> addTheater(@RequestBody TheaterRequest theaterRequest) {

         String theater = theaterService.addTheater(theaterRequest);

         return new ResponseEntity<>(theater, HttpStatus.CREATED);
    }

    @PostMapping("/addTheaterSeat")
    public ResponseEntity<String> addTheaterSeat(@RequestBody TheaterSeatRequest theaterSeatRequest) {

        String theater = theaterService.addTheaterSeat(theaterSeatRequest);

        return new ResponseEntity<>(theater, HttpStatus.CREATED);
    }
}
