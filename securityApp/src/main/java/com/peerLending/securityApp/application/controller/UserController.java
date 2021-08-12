package com.peerLending.securityApp.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.peerLending.securityApp.user.entity.User;
import com.peerLending.securityApp.user.exception.UserNotFoundException;
import com.peerLending.securityApp.user.repository.UserRepository;
import com.peerLending.securityApp.user.serviceImpl.NotificationServiceImpl;

@RestController
@RequestMapping(path = { "/user" })
public class UserController {

	private final UserRepository userRepository;
	private final NotificationServiceImpl notificationServiceImpl;

	/**
	 * @param userRepository
	 */
	@Autowired
	public UserController(UserRepository userRepository, NotificationServiceImpl notificationServiceImpl) {
		super();
		this.userRepository = userRepository;
		this.notificationServiceImpl = notificationServiceImpl;
	}

	@PostMapping("/register")
	public void register(@RequestBody User user) {
		userRepository.save(user);
		notificationServiceImpl.sendMessage(user);
	}

	@GetMapping("/validate")
	public String validateTokenAndGetUserName(@RequestHeader(name = "Authorization") String token) {
		return userRepository.findById(token).orElseThrow(() -> new UserNotFoundException(token)).getUserName();
	}

}
