package com.peerLender.landingEngine.domain.service;

import java.util.List;

import com.peerLender.landingEngine.application.dtos.LoanRequestDto;
import com.peerLender.landingEngine.domain.model.LoanRequest;
import com.peerLender.landingEngine.domain.model.User;

public interface LoanRequestService {
	public LoanRequest transform(LoanRequestDto loanRequestDto, User user);
	public LoanRequestDto transform(LoanRequest loanRequest);
	public List<LoanRequestDto> findAllLoanRequestsAsLoanRequestDto();
}
