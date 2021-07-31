package com.peerLending.securityApp.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.peerLending.securityApp.user.exception.UserNotFoundException;
import com.peerLending.securityApp.user.repository.UserRepository;


@RestController
@RequestMapping(path = {"/user"})
public class UserController {
	
	private final UserRepository userRepository;
	
	/**
	 * @param userRepository
	 */
	@Autowired
	public UserController(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@RequestMapping("/validate")
	public String validateTokenAndGetUserName(@RequestHeader(name = "Authorization") String token) {
		return userRepository.findById(token).orElseThrow(() -> new UserNotFoundException(token)).getUserName();
	}

}
