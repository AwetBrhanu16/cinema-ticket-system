package com.example.cinema_ticket_system.service;

import com.example.cinema_ticket_system.converter.TheaterConverter;
import com.example.cinema_ticket_system.entities.Theater;
import com.example.cinema_ticket_system.entities.TheaterSeat;
import com.example.cinema_ticket_system.enums.SeatType;
import com.example.cinema_ticket_system.repository.TheaterRepo;
import com.example.cinema_ticket_system.request.TheaterRequest;
import com.example.cinema_ticket_system.request.TheaterSeatRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TheaterService {

    @Autowired
    private TheaterRepo theaterRepo;

    public String addTheater(TheaterRequest theaterRequest) {

        if (theaterRepo.findByAddress(theaterRequest.getAddress()) != null) {
            throw new RuntimeException("theater already exists");
        }

        Theater theater = TheaterConverter.theaterDtoToTheater(theaterRequest);
        theaterRepo.save(theater);

        return "theater added successfully";
    }

    public String addTheaterSeat(TheaterSeatRequest theaterSeatRequest) {

        // Check if the theater exists by address
        if (theaterRepo.findByAddress(theaterSeatRequest.getAddress()) == null) {
            throw new RuntimeException("no theater found");
        }

        Integer noOfSeatsInRow = theaterSeatRequest.getNoOfSeatInRow();
        Integer noOfPremiumSeats = theaterSeatRequest.getNoOfPremiumSeat();
        Integer noOfClassicSeat = theaterSeatRequest.getNoOfClassicSeat();
        String address = theaterSeatRequest.getAddress();

        // Retrieve the theater by address
        Theater theater = theaterRepo.findByAddress(address);

        // Get the current list of theater seats
        List<TheaterSeat> theaterSeats = theater.getTheaterSeatList();

        int fill = 0;
        int counter = 1;
        char ch = 'A';

        // Add classic seats
        for (int i = 1; i <= noOfClassicSeat; i++) {
            String seatNo = Integer.toString(counter) + ch;

            ch++;
            fill++;

            if (fill == noOfSeatsInRow) {
                fill = 0;
                counter++;
                ch = 'A';
            }

            TheaterSeat theaterSeat = new TheaterSeat();
            theaterSeat.setSeatNumber(seatNo);
            theaterSeat.setSeatType(SeatType.CLASSIC);
            theaterSeat.setTheater(theater);
            theaterSeats.add(theaterSeat);
        }

        // Add premium seats
        for (int i = 1; i <= noOfPremiumSeats; i++) {
            String seatNo = Integer.toString(counter) + ch;

            fill++;
            ch++;
            if (fill == noOfSeatsInRow) {
                fill = 0;
                counter++;
                ch = 'A';
            }

            TheaterSeat theaterSeat = new TheaterSeat();
            theaterSeat.setSeatNumber(seatNo);
            theaterSeat.setSeatType(SeatType.PREMIUM);
            theaterSeat.setTheater(theater);
            theaterSeats.add(theaterSeat);
        }

        // Print the size of the seats list
//        int size = theaterSeats.size();
//        System.out.println(size);

        // Save the theater with the updated seats
        theaterRepo.save(theater);

        return "theaterSeat added successfully";
    }

}
