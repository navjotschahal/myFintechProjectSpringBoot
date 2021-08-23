package com.peerLender.landingEngine.application.dtos;

import java.util.Objects;

public class LoanRequestDto {

	private String loanRequestId;
	private double loanAmount;
	private long repaymentTermDays;
	private double interestRate;

	public LoanRequestDto(String loanRequestId, double loanAmount, long repaymentTermDays, double interestRate) {
		this.loanRequestId = loanRequestId;
		this.loanAmount = loanAmount;
		this.repaymentTermDays = repaymentTermDays;
		this.interestRate = interestRate;
	}

	/**
	 * @return the loanRequestId
	 */
	public String getLoanRequestId() {
		return loanRequestId;
	}

	/**
	 * @param loanRequestId the loanRequestId to set
	 */
	public void setLoanRequestId(String loanRequestId) {
		this.loanRequestId = loanRequestId;
	}

	/**
	 * @return the loanAmount
	 */
	public double getLoanAmount() {
		return loanAmount;
	}

	/**
	 * @return the repaymentTermDays
	 */
	public long getRepaymentTermDays() {
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
		return Objects.hash(interestRate, loanAmount, repaymentTermDays);
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
		return Double.doubleToLongBits(interestRate) == Double.doubleToLongBits(other.interestRate)
				&& loanAmount == other.loanAmount && repaymentTermDays == other.repaymentTermDays;
	}

	@Override
	public String toString() {
		return "LoanRequestDto [loanAmount=" + loanAmount + ", repaymentTermDays=" + repaymentTermDays + ", interestRate=" + interestRate + "]";
	}
}
