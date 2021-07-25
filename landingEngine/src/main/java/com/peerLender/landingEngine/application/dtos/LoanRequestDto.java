package com.peerLender.landingEngine.application.dtos;

import java.util.Objects;

public class LoanRequestDto {

	private int loanAmount;
	private long borrowerUserId;
	private int repaymentTermDays;
	private double interestRate;

	public LoanRequestDto(int loanAmount, int borrowerUserId, int repaymentTermDays, double interestRate) {
		this.loanAmount = loanAmount;
		this.borrowerUserId = borrowerUserId;
		this.repaymentTermDays = repaymentTermDays;
		this.interestRate = interestRate;
	}

	/**
	 * @return the loanAmount
	 */
	public int getLoanAmount() {
		return loanAmount;
	}

	/**
	 * @return the borrowerUserId
	 */
	public long getBorrowerUserId() {
		return borrowerUserId;
	}

	/**
	 * @return the repaymentTermDays
	 */
	public int getRepaymentTermDays() {
		return repaymentTermDays;
	}

	/**
	 * @return the interestRate
	 */
	public double getInterestRate() {
		return interestRate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(borrowerUserId, interestRate, loanAmount, repaymentTermDays);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoanRequestDto other = (LoanRequestDto) obj;
		return borrowerUserId == other.borrowerUserId
				&& Double.doubleToLongBits(interestRate) == Double.doubleToLongBits(other.interestRate)
				&& loanAmount == other.loanAmount && repaymentTermDays == other.repaymentTermDays;
	}

	@Override
	public String toString() {
		return "LoanRequestDto [loanAmount=" + loanAmount + ", borrowerUserId=" + borrowerUserId
				+ ", repaymentTermDays=" + repaymentTermDays + ", interestRate=" + interestRate + "]";
	}
}
