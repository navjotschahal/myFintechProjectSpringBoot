package com.peerLender.landingEngine.domain.serviceimpl;

import java.time.Duration;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.peerLender.landingEngine.application.dtos.LoanRequestDto;
import com.peerLender.landingEngine.domain.exception.UserNotFoundException;
import com.peerLender.landingEngine.domain.model.LoanRequest;
import com.peerLender.landingEngine.domain.model.User;
import com.peerLender.landingEngine.domain.repository.UserRepository;
import com.peerLender.landingEngine.domain.service.LoanRequestService;

@Component
public class LoanRequestServiceImpl implements LoanRequestService {
	
	private final UserRepository userRepository;

	@Autowired
	public LoanRequestServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public LoanRequest transform(LoanRequestDto loanRequestDto) {
		Optional<User> borrower = userRepository.findById(loanRequestDto.getBorrowerUserId());
		if (borrower.isPresent()) {
			return new LoanRequest(
					loanRequestDto.getLoanAmount(),
					borrower.get(),
					Duration.ofDays(loanRequestDto.getRepaymentTermDays()),
					loanRequestDto.getInterestRate()
				);
		} else {
			throw new UserNotFoundException(loanRequestDto.getBorrowerUserId());
		}
	}

}
