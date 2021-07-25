package com.peerLender.landingEngine.application.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.peerLender.landingEngine.application.dtos.LoanRequestDto;
import com.peerLender.landingEngine.domain.model.LoanRequest;
import com.peerLender.landingEngine.domain.model.User;
import com.peerLender.landingEngine.domain.repository.LoanRequestRepository;
import com.peerLender.landingEngine.domain.repository.UserRepository;
import com.peerLender.landingEngine.domain.serviceimpl.LoanRequestServiceImpl;

@RestController
public class LoanController {
	
	private final LoanRequestRepository loanRequestRepository;
	private final UserRepository userRepository;
	private final LoanRequestServiceImpl loanRequestServiceImpl;
	
	@Autowired
	public LoanController(LoanRequestRepository loanRequestRepository, UserRepository userRepository, LoanRequestServiceImpl loanRequestServiceImpl) {
		super();
		this.loanRequestRepository = loanRequestRepository;
		this.userRepository = userRepository;
		this.loanRequestServiceImpl = loanRequestServiceImpl;
	}

	@PostMapping(value = {"loan/request"})
	private void requestLoan(@RequestBody final LoanRequestDto loanRequestDto) {
		LoanRequest loanRequest = loanRequestServiceImpl.transform(loanRequestDto);
		loanRequestRepository.save(loanRequest);
	}
	
	@GetMapping(value = {"loan/users"})
	private List<User> getLoanUsers() {
		return userRepository.findAll();
	}

}
