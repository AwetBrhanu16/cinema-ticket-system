package com.example.cinema_ticket_system.Exeptions;

import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class MovieAlreadyExist extends RuntimeException {
	private static final long serialVersionUID = 87214071728310561L;

	public MovieAlreadyExist() {
		super("Movie is already exists with same name and language");
	}
}
