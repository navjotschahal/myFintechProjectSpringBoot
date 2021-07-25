package com.peerLender.landingEngine.domain.exception;

public class LoanRequestNotFoundException extends RuntimeException {

	public LoanRequestNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LoanRequestNotFoundException(long loanRequestId) {
		super("Loan-request with ID: " + loanRequestId + " is not found.");
		// TODO Auto-generated constructor stub
	}

}
