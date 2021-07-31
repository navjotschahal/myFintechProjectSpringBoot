package com.peerLending.securityApp.user.exception;

public class UserNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	public UserNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public UserNotFoundException(String token) {
		super("Invalid token/id:" + token + " user not found!");
		// TODO Auto-generated constructor stub
	}

}
