package com.peerLender.landingEngine.domain.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.peerLender.landingEngine.domain.exception.LoanNotFoundException;
import com.peerLender.landingEngine.domain.exception.UserNotFoundException;
import com.peerLender.landingEngine.domain.model.Loan;
import com.peerLender.landingEngine.domain.model.LoanRequest;
import com.peerLender.landingEngine.domain.model.User;
import com.peerLender.landingEngine.domain.repository.LoanRepository;
import com.peerLender.landingEngine.domain.repository.LoanRequestRepository;
import com.peerLender.landingEngine.domain.repository.UserRepository;
import com.peerLender.landingEngine.domain.service.LoanService;

@Component
public class LoanServiceImpl implements LoanService {
	private final LoanRepository loanRepository;
	private final LoanRequestRepository loanRequestRepository;
	private final UserRepository userRepository;

	/**
	 * @param loanRepository
	 * @param loanRequestRepository
	 * @param userRepository
	 */
	@Autowired
	public LoanServiceImpl(LoanRepository loanRepository, LoanRequestRepository loanRequestRepository,
			UserRepository userRepository) {
		super();
		this.loanRepository = loanRepository;
		this.loanRequestRepository = loanRequestRepository;
		this.userRepository = userRepository;
	}

	public void acceptLoan(final long loanRequestId, final String lenderUserName) {
		User loanLenderUser = userRepository.findById(lenderUserName).orElseThrow(() -> new UserNotFoundException());
		LoanRequest loanRequest = loanRequestRepository.findById(loanRequestId).orElseThrow(() -> new LoanNotFoundException());
		loanRepository.save(new Loan(loanLenderUser, loanRequest));
	}

}
