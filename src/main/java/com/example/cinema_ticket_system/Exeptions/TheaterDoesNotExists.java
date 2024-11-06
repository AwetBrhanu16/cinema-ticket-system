package com.example.cinema_ticket_system.Exeptions;

public class TheaterDoesNotExists extends RuntimeException {
	private static final long serialVersionUID = 2885350098352987873L;

	public TheaterDoesNotExists() {
		super("Theater does not Exists");
	}
}
