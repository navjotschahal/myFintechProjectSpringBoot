package com.peerLender.landingEngine.domain.model;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Loan {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@ManyToOne
	private User borrower;
	@ManyToOne
	private User lender;
	private long loanAmount;
	private double interestRate;
	private LocalDate dateLent;
	private LocalDate dateDue;

	public Loan() {
		super();
	}

	public Loan(User borrower, User lender, long loanAmount, double interestRate, LocalDate dateLent,
			LocalDate dateDue) {
		super();
		this.borrower = borrower;
		this.lender = lender;
		this.loanAmount = loanAmount;
		this.interestRate = interestRate;
		this.dateLent = dateLent;
		this.dateDue = dateDue;
	}
	
	public Loan(User lender, LoanRequest loanRequest) {
		super();
		this.borrower = loanRequest.getBorrower();
		this.lender = lender;
		this.loanAmount = loanRequest.getLoanAmount();
		this.interestRate = loanRequest.getInterestRate();
		this.dateLent = LocalDate.now();
		this.dateDue = LocalDate.now().plusDays(loanRequest.getRepaymentTerm().toDays());
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the borrower
	 */
	public User getBorrower() {
		return borrower;
	}

	/**
	 * @param borrower the borrower to set
	 */
	public void setBorrower(User borrower) {
		this.borrower = borrower;
	}

	/**
	 * @return the lender
	 */
	public User getLender() {
		return lender;
	}

	/**
	 * @param lender the lender to set
	 */
	public void setLender(User lender) {
		this.lender = lender;
	}

	/**
	 * @return the loanAmount
	 */
	public long getLoanAmount() {
		return loanAmount;
	}

	/**
	 * @param loanAmount the loanAmount to set
	 */
	public void setLoanAmount(long loanAmount) {
		this.loanAmount = loanAmount;
	}

	/**
	 * @return the interestRate
	 */
	public double getInterestRate() {
		return interestRate;
	}

	/**
	 * @param interestRate the interestRate to set
	 */
	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	/**
	 * @return the dateLent
	 */
	public LocalDate getDateLent() {
		return dateLent;
	}

	/**
	 * @param dateLent the dateLent to set
	 */
	public void setDateLent(LocalDate dateLent) {
		this.dateLent = dateLent;
	}

	/**
	 * @return the dateDue
	 */
	public LocalDate getDateDue() {
		return dateDue;
	}

	/**
	 * @param dateDue the dateDue to set
	 */
	public void setDateDue(LocalDate dateDue) {
		this.dateDue = dateDue;
	}

	@Override
	public int hashCode() {
		return Objects.hash(borrower, dateDue, dateLent, id, interestRate, lender, loanAmount);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Loan other = (Loan) obj;
		return Objects.equals(borrower, other.borrower) && Objects.equals(dateDue, other.dateDue)
				&& Objects.equals(dateLent, other.dateLent) && id == other.id
				&& Double.doubleToLongBits(interestRate) == Double.doubleToLongBits(other.interestRate)
				&& Objects.equals(lender, other.lender) && loanAmount == other.loanAmount;
	}

	@Override
	public String toString() {
		return "Loan [id=" + id + ", borrower=" + borrower + ", lender=" + lender + ", loanAmount=" + loanAmount
				+ ", interestRate=" + interestRate + ", dateLent=" + dateLent + ", dateDue=" + dateDue + "]";
	}
}
