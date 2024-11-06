package com.example.cinema_ticket_system.controller;

import com.example.cinema_ticket_system.request.MovieRequest;
import com.example.cinema_ticket_system.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @PostMapping("addMovie")
    public ResponseEntity<String> addMovie(@RequestBody MovieRequest movieRequest) {
      String result = movieService.addMovie(movieRequest);
      return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/getMovies")
    public List<MovieRequest> getAllMovies(){
       return movieService.getAllMovies();
    }
}
