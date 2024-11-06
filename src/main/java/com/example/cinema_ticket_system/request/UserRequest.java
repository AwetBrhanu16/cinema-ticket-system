package com.example.cinema_ticket_system.request;

import com.example.cinema_ticket_system.enums.Gender;
import lombok.Data;

@Data
public class UserRequest {
    private Integer userId;
    private String firstName;
    private String lastName;
    private String userName;
    private Integer age;
    private String address;
    private String mobileNo;
    private String emailId;
    private Gender gender;
    private String roles;
    private String password;
}