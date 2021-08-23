package com.peerLender.landingEngine.application.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.peerLender.landingEngine.domain.model.User;
import com.peerLender.landingEngine.domain.repository.UserRepository;
import com.peerLender.landingEngine.domain.serviceimpl.LoanServiceImpl;
import com.peerLender.landingEngine.domain.serviceimpl.TokenValidationServiceImpl;

@RestController
public class UserController {

	private final TokenValidationServiceImpl tokenValidationServiceImpl;
	private final UserRepository userRepository;
	/**
	 * @param userRepository
	 * @param tokenValidationServiceImpl
	 */
	@Autowired
	public UserController(TokenValidationServiceImpl tokenValidationServiceImpl, UserRepository userRepository) {
		super();
		this.tokenValidationServiceImpl = tokenValidationServiceImpl;
		this.userRepository = userRepository;
	}
	
	@GetMapping(value = {"loan/users"})
	private List<User> getLoanUsers(HttpServletRequest request) {
		tokenValidationServiceImpl.validateTokenAndGetUser(request.getHeader(HttpHeaders.AUTHORIZATION));
		return userRepository.findAll();
	}
}
