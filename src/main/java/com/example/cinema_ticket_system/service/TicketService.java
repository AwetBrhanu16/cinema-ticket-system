package com.example.cinema_ticket_system.service;

import com.example.cinema_ticket_system.converter.TicketConverter;
import com.example.cinema_ticket_system.entities.Show;
import com.example.cinema_ticket_system.entities.ShowSeat;
import com.example.cinema_ticket_system.entities.Ticket;
import com.example.cinema_ticket_system.entities.User;
import com.example.cinema_ticket_system.repository.ShowRepo;
import com.example.cinema_ticket_system.repository.TicketRepo;
import com.example.cinema_ticket_system.repository.UsersRepo;
import com.example.cinema_ticket_system.request.TicketRequest;
import com.example.cinema_ticket_system.response.TicketResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    @Autowired
    public UsersRepo usersRepo;

    @Autowired
    public ShowRepo showRepo;

    @Autowired
    private TicketRepo ticketRepo;

    public TicketResponse addNewTicket(TicketRequest ticketRequest) {

        Optional<Show> showOptional = showRepo.findById(ticketRequest.getShowId());

        if (showOptional.isEmpty()) {
            throw new RuntimeException("show not found");
        }

        Optional<User> userOptional = usersRepo.findById(ticketRequest.getUserId());

        if (userOptional.isEmpty()) {
            throw new RuntimeException("user not found");
        }

        User user = userOptional.get();
        Show show = showOptional.get();

        boolean isAvailable = isSeatAvailable(show.getShowSeatList(),ticketRequest.getRequestSeats());

        if (!isAvailable) {
            throw new RuntimeException("seat not available");
        }

        Integer getPriceAndAssignSeat = getPriceAndAssignSeats(show.getShowSeatList(), ticketRequest.getRequestSeats());

        String seats = listToString(ticketRequest.getRequestSeats());

        Ticket ticket = new Ticket();
        ticket.setShow(show);
        ticket.setUser(user);
        ticket.setBookedSeats(seats);
        ticket.setTotalTicketPrice(getPriceAndAssignSeat);

        ticket = ticketRepo.save(ticket);

        user.getTicketList().add(ticket);
        show.getTicketList().add(ticket);
        usersRepo.save(user);
        showRepo.save(show);

        return TicketConverter.returnTicket(show, ticket);

    }


    private Boolean isSeatAvailable(List<ShowSeat> showSeatList, List<String> requestSeats) {
        for (ShowSeat showSeat : showSeatList) {
            String seatNo = showSeat.getSeatNo();

            if (requestSeats.contains(seatNo) && !showSeat.isSeatAvailable()) {
                return false;
            }
        }

        return true;
    }

    private Integer getPriceAndAssignSeats(List<ShowSeat> showSeatList, List<String> requestSeats) {
        Integer totalAmount = 0;

        for (ShowSeat showSeat : showSeatList) {
            if (requestSeats.contains(showSeat.getSeatNo())) {
                totalAmount += showSeat.getPrice();
                showSeat.setSeatAvailable(Boolean.FALSE);
            }
        }

        return totalAmount;
    }

    private String listToString(List<String> requestSeats) {
        StringBuilder sb = new StringBuilder();

        for (String s : requestSeats) {
            sb.append(s).append(",");
        }

        return sb.toString();
    }
}
