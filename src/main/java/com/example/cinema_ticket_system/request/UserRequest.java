package com.example.cinema_ticket_system.request;

import com.example.cinema_ticket_system.enums.Gender;
import com.example.cinema_ticket_system.enums.UserRoles;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
public class UserRequest {
    private Integer id;
    private String firstName;
    private String lastName;
    private String userName;
    private Integer age;
    private String address;
    private String mobileNo;
    private String email;
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private UserRoles roles;
    private String password;
}