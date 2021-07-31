package com.peerLender.landingEngine.domain.service;

public interface LoanService {
	public void acceptLoan(final long loanRequestId, final String lenderUserName);
}
