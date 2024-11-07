package com.example.cinema_ticket_system.service;

import com.example.cinema_ticket_system.config.JwtService;
import com.example.cinema_ticket_system.converter.UserConvertor;
import com.example.cinema_ticket_system.entities.User;
import com.example.cinema_ticket_system.repository.UsersRepo;
import com.example.cinema_ticket_system.request.UserLoginRequest;
import com.example.cinema_ticket_system.request.UserRequest;
import com.example.cinema_ticket_system.response.AuthResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG =
            "user with email %s not found";

    private final UsersRepo userRepo;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;
    private final BCryptPasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
            return  userRepo.findByUserName(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG,username)));

    }


    public String registerUser(UserRequest userRequest) {
        System.out.println("there is a problem");
        Optional<User> user = userRepo.findByUserName(userRequest.getUserName());

       boolean isExist = userRepo
               .findByEmailId(userRequest
                       .getEmail()).isPresent();


        String encodedPassword = passwordEncoder.encode(userRequest.getPassword());
        userRequest.setPassword(encodedPassword);
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
            userRequest.setEmail(user.getEmailId());
            userRequest.setMobileNo(user.getMobileNo());
            userRequest.setRoles(user.getUserRoles());
            userRequest.setPassword(user.getPassword());
            userRequest.setId(user.getId());
            return userRequest;
        }).collect(Collectors.toList());

    }

    public int enableUser(String email) {
        return userRepo.enableAppUser(email);
    }



}
