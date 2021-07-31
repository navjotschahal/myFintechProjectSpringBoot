package com.peerLender.landingEngine.domain.exception;

public class LoanNotFoundException extends RuntimeException {

	public LoanNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LoanNotFoundException(long loanId) {
		super("Loan with ID: " + loanId + " is not found.");
		// TODO Auto-generated constructor stub
	}

}
