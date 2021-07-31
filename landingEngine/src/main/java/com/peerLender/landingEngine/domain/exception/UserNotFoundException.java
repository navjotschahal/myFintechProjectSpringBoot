package com.peerLender.landingEngine.domain.exception;

public class UserNotFoundException extends RuntimeException {

	public UserNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserNotFoundException(String userName) {
		super("User with userName: " + userName + " is not found/doesn't exist.");
	}

}
