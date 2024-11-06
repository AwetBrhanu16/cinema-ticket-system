package com.example.cinema_ticket_system.converter;

import com.example.cinema_ticket_system.entities.User;
import com.example.cinema_ticket_system.request.UserRequest;
import com.example.cinema_ticket_system.response.UserResponse;


public class UserConvertor {

    public static User userDtoToUser(UserRequest userRequest) {
        User user = User.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .userName(userRequest.getUserName())
                .mobileNo(userRequest.getMobileNo())
                .emailId(userRequest.getEmailId())
                .roles(userRequest.getRoles())
                .password(userRequest.getPassword())
                .build();

        return user;
    }

    public static UserResponse userToUserDto(User user) {
        UserResponse userDto = UserResponse.builder()
                .name(user.getUsername())
                .build();

        return userDto;
    }
}
