package com.example.cinema_ticket_system.service;

import com.example.cinema_ticket_system.config.JwtService;
import com.example.cinema_ticket_system.converter.UserConvertor;
import com.example.cinema_ticket_system.entities.User;
import com.example.cinema_ticket_system.repository.UsersRepo;
import com.example.cinema_ticket_system.request.UserLoginRequest;
import com.example.cinema_ticket_system.request.UserRequest;
import com.example.cinema_ticket_system.response.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class UserService {

    @Autowired
    private UsersRepo userRepo;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authManager;

    public String addUser(UserRequest userRequest) {

        System.out.println("there is a problem");
        Optional<User> user = userRepo.findByUserName(userRequest.getUserName());

        if (user.isPresent()) {
            return "User already exists";
        }


        User convertedUser = UserConvertor.userDtoToUser(userRequest);

        userRepo.save(convertedUser);
        return "User Saved Successfully";
    }


    public ResponseEntity<AuthResponse> verifyUser(UserLoginRequest userLoginRequest) {

        Authentication authentication =
                authManager.authenticate(
                        new UsernamePasswordAuthenticationToken(userLoginRequest.getUserName(),
                                userLoginRequest.getPassword()));


        if (authentication.isAuthenticated()) {
            String token = jwtService.generateToken(userLoginRequest.getUserName());

            return ResponseEntity.status(HttpStatus.OK)
                    .body(AuthResponse.builder()
                            .token(token)
                            .build());
        }else {
            throw new UsernameNotFoundException("Wrong credential.");
        }
    }

    public List<UserRequest> getAllUsers() {

        List<User> users = userRepo.findAll();

        return users.stream().map(user ->{
            UserRequest userRequest = new UserRequest();

            userRequest.setUserName(user.getUsername());
            userRequest.setFirstName(user.getFirstName());
            userRequest.setLastName(user.getLastName());
            userRequest.setEmailId(user.getEmailId());
            userRequest.setMobileNo(user.getMobileNo());
            userRequest.setRoles(user.getRoles());
            userRequest.setPassword(user.getPassword());
            userRequest.setUserId(user.getId());
            return userRequest;
        }).collect(Collectors.toList());

    }
}
