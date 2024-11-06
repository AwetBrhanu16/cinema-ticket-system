package com.example.cinema_ticket_system.entities;

import com.example.cinema_ticket_system.enums.SeatType;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "SHOW_SEATS")
public class ShowSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String seatNo;

    @Enumerated(EnumType.STRING)
    private SeatType seatType;

    private Integer price;

    private boolean isSeatAvailable;

    private boolean isFoodContains;

    @ManyToOne
    @JoinColumn
    private Show show;
}
