package com.peerLending.profileApp.application.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.peerLending.profileApp.domain.model.User;
import com.peerLending.profileApp.domain.repository.UserRepository;

@RestController
@RequestMapping(path = {"/profile"})
public class ProfileController {
	private final UserRepository userRepository;

	/**
	 * @param userRepository
	 */
	@Autowired
	public ProfileController(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}
	
	@GetMapping("/users")
	public List<User> getUsers() {
		return userRepository.findAll();
	}
	
	@PostMapping("/user")
	public void newUser(@RequestBody final User newUser) {
		userRepository.save(newUser);
	}
	
	@PutMapping("/user")
	public void updateUser(@RequestBody final User updatedUser) {
		updatedUser.setRegisteredSince(LocalDate.now());
		userRepository.save(updatedUser);
	}
}
