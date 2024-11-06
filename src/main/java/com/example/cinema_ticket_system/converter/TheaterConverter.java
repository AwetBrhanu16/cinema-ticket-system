package com.example.cinema_ticket_system.converter;

import com.example.cinema_ticket_system.entities.Theater;
import com.example.cinema_ticket_system.request.TheaterRequest;


public class TheaterConverter {

    public static Theater theaterDtoToTheater(TheaterRequest theaterRequest) {
        Theater theater = Theater
                .builder()
                .name(theaterRequest.getName())
                .address(theaterRequest.getAddress())
                .build();

        return theater;
    }
}
