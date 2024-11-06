package com.example.cinema_ticket_system.converter;

import com.example.cinema_ticket_system.entities.Show;
import com.example.cinema_ticket_system.entities.Ticket;
import com.example.cinema_ticket_system.response.TicketResponse;

public class TicketConverter {

    public static TicketResponse returnTicket(Show show, Ticket ticket) {

        TicketResponse ticketResponseDto = TicketResponse.builder()
                .bookedSeats(ticket.getBookedSeats())
                .address(show.getTheater().getAddress())
                .theaterName(show.getTheater().getName())
                .movieName(show.getMovie().getMovieName())
                .date(show.getDate())
                .time(show.getTime())
                .totalPrice(ticket.getTotalTicketPrice())
                .build();

        return ticketResponseDto;
    }
}
