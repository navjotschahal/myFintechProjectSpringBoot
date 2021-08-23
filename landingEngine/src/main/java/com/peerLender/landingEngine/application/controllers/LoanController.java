package com.peerLender.landingEngine.application.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.peerLender.landingEngine.application.dtos.LoanRequestDto;
import com.peerLender.landingEngine.domain.model.Loan;
import com.peerLender.landingEngine.domain.model.LoanRepaymentRequest;
import com.peerLender.landingEngine.domain.model.LoanRequest;
import com.peerLender.landingEngine.domain.model.User;
import com.peerLender.landingEngine.domain.repository.LoanRepository;
import com.peerLender.landingEngine.domain.repository.LoanRequestRepository;
import com.peerLender.landingEngine.domain.repository.UserRepository;
import com.peerLender.landingEngine.domain.serviceimpl.LoanRequestServiceImpl;
import com.peerLender.landingEngine.domain.serviceimpl.LoanServiceImpl;
import com.peerLender.landingEngine.domain.serviceimpl.TokenValidationServiceImpl;

@RestController
public class LoanController {

	private final LoanRequestRepository loanRequestRepository;
	private final LoanRepository loanRepository;
	private final LoanRequestServiceImpl loanRequestServiceImpl;
	private final LoanServiceImpl loanServiceImpl;
	private final TokenValidationServiceImpl tokenValidationServiceImpl;

	@Autowired
	public LoanController(LoanRequestRepository loanRequestRepository, LoanRequestServiceImpl loanRequestServiceImpl,
			LoanServiceImpl loanServiceImpl, LoanRepository loanRepository,
			TokenValidationServiceImpl tokenValidationServiceImpl) {
		super();
		this.loanRequestRepository = loanRequestRepository;
		this.loanRepository = loanRepository;
		this.loanRequestServiceImpl = loanRequestServiceImpl;
		this.loanServiceImpl = loanServiceImpl;
		this.tokenValidationServiceImpl = tokenValidationServiceImpl;
	}

	@PostMapping(value = { "loan/request" })
	private LoanRequest requestLoan(@RequestBody final LoanRequestDto loanRequestDto, HttpServletRequest request) {
		User borrower = tokenValidationServiceImpl
				.validateTokenAndGetUser(request.getHeader(HttpHeaders.AUTHORIZATION));
		LoanRequest loanRequest = loanRequestServiceImpl.transform(loanRequestDto, borrower);
		return loanRequestRepository.save(loanRequest);
	}

	@PostMapping(value = { "loan/request/accept/{loanRequestId}" })
	private void requestLoan(@PathVariable final String loanRequestId, HttpServletRequest request) {
		User user = tokenValidationServiceImpl.validateTokenAndGetUser(request.getHeader(HttpHeaders.AUTHORIZATION));
		loanServiceImpl.acceptLoan(Long.parseLong(loanRequestId), user.getUserName());
	}

	@GetMapping(value = { "loan/requests" })
	private List<LoanRequest> getLoanRequests(HttpServletRequest request) {
		tokenValidationServiceImpl.validateTokenAndGetUser(request.getHeader(HttpHeaders.AUTHORIZATION));
		return loanRequestRepository.findAll();
	}

	@GetMapping(value = { "loan/loans" })
	private List<Loan> getLoans(HttpServletRequest request) {
		tokenValidationServiceImpl.validateTokenAndGetUser(request.getHeader(HttpHeaders.AUTHORIZATION));
		return loanRepository.findAll();
	}

	@GetMapping(value = { "loan/borrowed" })
	private List<Loan> findBorrowedLoans(HttpServletRequest request) {
		User borrower = tokenValidationServiceImpl
				.validateTokenAndGetUser(request.getHeader(HttpHeaders.AUTHORIZATION));

		return loanServiceImpl.findAllBrorrowedLoans(borrower);
	}

	@GetMapping(value = { "loan/lent" })
	private List<Loan> findLentLoans(HttpServletRequest request) {
		User borrower = tokenValidationServiceImpl
				.validateTokenAndGetUser(request.getHeader(HttpHeaders.AUTHORIZATION));

		return loanServiceImpl.findAllLentLoans(borrower);
	}
	
	@PostMapping("/loan/repay")
	public void repayLoan(@RequestBody LoanRepaymentRequest loanRepaymentRequest, @RequestHeader String authorization) {
		User borrower = this.tokenValidationServiceImpl.validateTokenAndGetUser(authorization);
		loanServiceImpl.repayLoan(loanRepaymentRequest.getAmount(), loanRepaymentRequest.getLoanId(), borrower);
	}

}
