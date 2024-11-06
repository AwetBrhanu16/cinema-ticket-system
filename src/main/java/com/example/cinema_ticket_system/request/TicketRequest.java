package com.example.cinema_ticket_system.request;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class TicketRequest {

    private Integer showId;
    private Integer userId;
    private List<String> requestSeats;

}
