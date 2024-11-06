package com.example.cinema_ticket_system.service;

import com.example.cinema_ticket_system.converter.MovieConverter;
import com.example.cinema_ticket_system.entities.Movie;
import com.example.cinema_ticket_system.repository.MovieRepo;
import com.example.cinema_ticket_system.request.MovieRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {

    @Autowired
    private MovieRepo movieRepo;

    public String addMovie(MovieRequest movieRequest) {

        Movie movieByName = movieRepo.findByMovieName(movieRequest.getMovieName());

        if (movieByName != null && movieByName.getLanguage().equals(movieRequest.getLanguage())) {
            throw new RuntimeException("movie already exists");
        }

        Movie movie = MovieConverter.movieDtoToMovie(movieRequest);
        movieRepo.save(movie);

        return "Movie added successfully";
    }
    public List<MovieRequest> getAllMovies() {
        List<Movie> movies = movieRepo.findAll();
        return movieRepo.findAll().stream().map(movie ->
             new MovieRequest(
                     movie.getMovieName(),
                     movie.getDuration(),
                     movie.getRating(),
                     movie.getReleaseDate(),
                     movie.getGenre(),
                     movie.getLanguage()
             )).collect(Collectors.toList());
         }

    }
