package com.peerLender.landingEngine.domain.service;

import com.peerLender.landingEngine.application.dtos.LoanRequestDto;
import com.peerLender.landingEngine.domain.model.LoanRequest;

public interface LoanRequestService {
	public LoanRequest transform(LoanRequestDto loanRequestDto);
}
