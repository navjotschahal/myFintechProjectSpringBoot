package com.peerLender.landingEngine.domain.model;

import java.time.Duration;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.peerLender.landingEngine.application.enums.Currency;
import com.peerLender.landingEngine.application.enums.Status;

@Entity
public class LoanRequest {

	@Id()
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private double loanAmount;
	@ManyToOne
	private User borrower;
	private Duration repaymentTerm;
	private double interestRate;
	private Status status;

	public LoanRequest() {
		super();
	}

	public LoanRequest(double loanAmount, User borrower, Duration repaymentTerm, double interestRate) {
		super();
		this.loanAmount = loanAmount;
		this.borrower = borrower;
		this.repaymentTerm = repaymentTerm;
		this.interestRate = interestRate;
		this.status = Status.ONGOING;
	}

	public Loan acceptLoanApplication(User lender) {
		lender.withDrawl(getLoanAmount());
		borrower.topUp(getLoanAmount());
		this.status = Status.COMPLETE;
		return new Loan(lender, this);
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @return the loanAmount
	 */
	public Money getLoanAmount() {
		return new Money(Currency.USD, this.loanAmount);
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
		return Objects.hash(borrower, id, interestRate, loanAmount, repaymentTerm, status);
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
		return Objects.equals(borrower, other.borrower) && id == other.id
				&& Double.doubleToLongBits(interestRate) == Double.doubleToLongBits(other.interestRate)
				&& Double.doubleToLongBits(loanAmount) == Double.doubleToLongBits(other.loanAmount)
				&& Objects.equals(repaymentTerm, other.repaymentTerm) && status == other.status;
	}

	@Override
	public String toString() {
		return "LoanRequest [loanAmount=" + loanAmount + ", borrower=" + borrower + ", repaymentTerm=" + repaymentTerm
				+ ", interestRate=" + interestRate + "]";
	}

}
