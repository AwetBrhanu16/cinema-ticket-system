package com.example.cinema_ticket_system.request;

import lombok.Data;

@Data
public class UserLoginRequest {

    private String userName;
    private String password;
}
