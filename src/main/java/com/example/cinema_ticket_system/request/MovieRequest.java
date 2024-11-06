package com.example.cinema_ticket_system.request;

import com.example.cinema_ticket_system.enums.Genre;
import com.example.cinema_ticket_system.enums.Language;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class MovieRequest {

    private String movieName;
    private Integer duration;
    private Double rating;
    private Date releaseDate;
    private Genre genre;
    private Language language;
}
