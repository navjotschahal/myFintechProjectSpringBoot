package com.peerLender.landingEngine.domain.model;

import java.time.Duration;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class LoanRequest {

	@Id()
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private int loanAmount;
	@ManyToOne
	private User borrower;
	private Duration repaymentTerm;
	private double interestRate;

	public LoanRequest(int loanAmount, User borrower, Duration repaymentTerm, double interestRate) {
		super();
		this.loanAmount = loanAmount;
		this.borrower = borrower;
		this.repaymentTerm = repaymentTerm;
		this.interestRate = interestRate;
	}

	/**
	 * @return the loanAmount
	 */
	public int getLoanAmount() {
		return loanAmount;
	}

	/**
	 * @return the borrower
	 */
	public User getBorrower() {
		return borrower;
	}

	/**
	 * @return the repaymentTerm
	 */
	public Duration getRepaymentTerm() {
		return repaymentTerm;
	}

	/**
	 * @return the interestRate
	 */
	public double getInterestRate() {
		return interestRate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(borrower, interestRate, loanAmount, repaymentTerm);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoanRequest other = (LoanRequest) obj;
		return Objects.equals(borrower, other.borrower)
				&& Double.doubleToLongBits(interestRate) == Double.doubleToLongBits(other.interestRate)
				&& loanAmount == other.loanAmount && Objects.equals(repaymentTerm, other.repaymentTerm);
	}

	@Override
	public String toString() {
		return "LoanRequest [loanAmount=" + loanAmount + ", borrower=" + borrower + ", repaymentTerm=" + repaymentTerm
				+ ", interestRate=" + interestRate + "]";
	}

}
