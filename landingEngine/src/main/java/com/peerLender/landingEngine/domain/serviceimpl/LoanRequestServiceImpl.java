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

	public LoanRequest transform(LoanRequestDto loanRequestDto, User user) {
			return new LoanRequest(
					loanRequestDto.getLoanAmount(),
					user,
					Duration.ofDays(loanRequestDto.getRepaymentTermDays()),
					loanRequestDto.getInterestRate()
				);
	}
	
	public LoanRequestDto transform(LoanRequest loanRequest) {
		boolean loanRequestexists = loanRequestRepository.existsById(loanRequest.getId());
		String borrowerUserName = loanRequest.getBorrower().getUserName();
		boolean borrowerUserexists = userRepository.existsById(borrowerUserName);
		if (borrowerUserexists && loanRequestexists) {
			return new LoanRequestDto(
					String.valueOf(loanRequest.getId()),
					loanRequest.getLoanAmount(),
					loanRequest.getRepaymentTerm().toDays(),
					loanRequest.getInterestRate()
				);
		} else {
			throw loanRequestexists ? new UserNotFoundException(borrowerUserName) : new LoanRequestNotFoundException(loanRequest.getId());
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
