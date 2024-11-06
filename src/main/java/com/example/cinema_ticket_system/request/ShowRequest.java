package com.example.cinema_ticket_system.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Data
@AllArgsConstructor
public class ShowRequest {


    private Long movieId;
    private Integer theaterId;
    private Integer showId;
    private Time time;
    private Date date;
    private MovieRequest movie;

}
