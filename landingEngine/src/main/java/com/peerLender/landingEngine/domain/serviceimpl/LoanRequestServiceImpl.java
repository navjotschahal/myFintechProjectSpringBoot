package com.peerLender.landingEngine.domain.serviceimpl;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.peerLender.landingEngine.application.dtos.LoanRequestDto;
import com.peerLender.landingEngine.domain.exception.LoanRequestNotFoundException;
import com.peerLender.landingEngine.domain.exception.UserNotFoundException;
import com.peerLender.landingEngine.domain.model.LoanRequest;
import com.peerLender.landingEngine.domain.model.User;
import com.peerLender.landingEngine.domain.repository.LoanRequestRepository;
import com.peerLender.landingEngine.domain.repository.UserRepository;
import com.peerLender.landingEngine.domain.service.LoanRequestService;

@Component
public class LoanRequestServiceImpl implements LoanRequestService {
	
	private final UserRepository userRepository;
	private final LoanRequestRepository loanRequestRepository;

	@Autowired
	public LoanRequestServiceImpl(UserRepository userRepository, LoanRequestRepository loanRequestRepository) {
		super();
		this.userRepository = userRepository;
		this.loanRequestRepository = loanRequestRepository;
	}

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
	
	public LoanRequestDto transform(LoanRequest loanRequest) {
		boolean loanRequestexists = loanRequestRepository.existsById(loanRequest.getId());
		long borrowerId = loanRequest.getBorrower().getId();
		boolean borrowerUserexists = userRepository.existsById(borrowerId);
		if (borrowerUserexists && loanRequestexists) {
			return new LoanRequestDto(
					loanRequest.getLoanAmount(),
					loanRequest.getBorrower().getId(),
					loanRequest.getRepaymentTerm().toDays(),
					loanRequest.getInterestRate()
				);
		} else {
			throw loanRequestexists ? new UserNotFoundException(borrowerId) : new LoanRequestNotFoundException(loanRequest.getId());
		}
	}
	
	public List<LoanRequestDto> findAllLoanRequestsAsLoanRequestDto() {
		List<LoanRequestDto> loanRequestDtoList = new ArrayList<LoanRequestDto>();
		List<LoanRequest> loanRequestList = loanRequestRepository.findAll();
		for (LoanRequest loanRequest : loanRequestList) {
			loanRequestDtoList.add(transform(loanRequest));
		}
		return loanRequestDtoList;
	}

}
