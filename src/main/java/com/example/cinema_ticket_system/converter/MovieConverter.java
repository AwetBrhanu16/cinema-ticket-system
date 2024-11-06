package com.example.cinema_ticket_system.converter;

import com.example.cinema_ticket_system.entities.Movie;
import com.example.cinema_ticket_system.request.MovieRequest;

public class MovieConverter {

    public static Movie movieDtoToMovie(MovieRequest movieRequest) {

        return Movie.builder()
                .movieName(movieRequest.getMovieName())
                .duration(movieRequest.getDuration())
                .rating(movieRequest.getRating())
                .releaseDate(movieRequest.getReleaseDate())
                .genre(movieRequest.getGenre())
                .language(movieRequest.getLanguage())
                .build();

    }
}
