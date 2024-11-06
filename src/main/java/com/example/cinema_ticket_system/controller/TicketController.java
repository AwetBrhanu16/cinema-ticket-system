package com.example.cinema_ticket_system.controller;

import com.example.cinema_ticket_system.entities.Ticket;
import com.example.cinema_ticket_system.request.TicketRequest;
import com.example.cinema_ticket_system.response.TicketResponse;
import com.example.cinema_ticket_system.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping("/booking")
    public ResponseEntity<TicketResponse> ticketBooking(@RequestBody TicketRequest ticketRequest) {

        TicketResponse ticket = ticketService.addNewTicket(ticketRequest);

        return new ResponseEntity<>(ticket, HttpStatus.CREATED);
    }
}
