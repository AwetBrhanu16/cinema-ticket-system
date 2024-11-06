package com.example.cinema_ticket_system.converter;

import com.example.cinema_ticket_system.entities.Show;
import com.example.cinema_ticket_system.request.ShowRequest;

public class ShowConverter {

    public static Show showDtoToShow(ShowRequest showRequest) {

        Show show = Show.builder()
                .showId(showRequest.getShowId())
                .time(showRequest.getTime())
                .date(showRequest.getDate())
                .build();
        return show;
    }
}
