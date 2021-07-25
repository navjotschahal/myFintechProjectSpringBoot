package com.peerLender.landingEngine.domain.exception;

public class UserNotFoundException extends RuntimeException {

	public UserNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserNotFoundException(long userId) {
		super("User with ID: " + userId + " is not found.");
	}

}
