package com.peerLender.landingEngine.domain.model;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;

import com.peerLender.landingEngine.application.enums.Status;

@Entity
public class Loan {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@ManyToOne
	private User borrower;
	@ManyToOne
	private User lender;
	@OneToOne(cascade = CascadeType.ALL)
	private Money loanAmount;
	private double interestRate;
	private LocalDate dateLent;
	private LocalDate dateDue;
	@OneToOne(cascade = CascadeType.ALL)
	private Money amountRepayed;
	private Status status;

	public Loan() {
		super();
	}

	public Loan(User borrower, User lender, Money loanAmount, double interestRate, LocalDate dateLent,
			LocalDate dateDue) {
		super();
		this.borrower = borrower;
		this.lender = lender;
		this.loanAmount = loanAmount;
		this.interestRate = interestRate;
		this.dateLent = dateLent;
		this.dateDue = dateDue;
		this.amountRepayed = Money.ZERO;
	}

	public Loan(User lender, LoanRequest loanRequest) {
		super();
		this.borrower = loanRequest.getBorrower();
		this.lender = lender;
		this.loanAmount = loanRequest.getLoanAmount();
		this.interestRate = loanRequest.getInterestRate();
		this.dateLent = LocalDate.now();
		this.dateDue = LocalDate.now().plusDays(loanRequest.getRepaymentTerm().toDays());
		this.amountRepayed = Money.ZERO;
		this.status = Status.ONGOING;
	}

	public void repay(final Money money) {
		this.borrower.withDrawl(money);
		this.lender.topUp(money);
		this.amountRepayed = this.amountRepayed.add(money);
		if (getAmountOwed().equals(Money.ZERO)) {
			this.status = Status.COMPLETE;
		}
	}

	public Money getAmountOwed() {
		Money remainingOwedLoanAmount = loanAmount.times(1 + this.interestRate / 100d).minus(amountRepayed);
		return remainingOwedLoanAmount;
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
	public Money getLoanAmount() {
		return loanAmount;
	}

	/**
	 * @param loanAmount the loanAmount to set
	 */
	public void setLoanAmount(Money loanAmount) {
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

	/**
	 * @return the amountRepayed
	 */
	public Money getAmountRepayed() {
		return amountRepayed;
	}

	/**
	 * @param amountRepayed the amountRepayed to set
	 */
	public void setAmountRepayed(Money amountRepayed) {
		this.amountRepayed = amountRepayed;
	}

	@Override
	public int hashCode() {
		return Objects.hash(amountRepayed, borrower, dateDue, dateLent, id, interestRate, lender, loanAmount, status);
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
		return Objects.equals(amountRepayed, other.amountRepayed) && Objects.equals(borrower, other.borrower)
				&& Objects.equals(dateDue, other.dateDue) && Objects.equals(dateLent, other.dateLent) && id == other.id
				&& Double.doubleToLongBits(interestRate) == Double.doubleToLongBits(other.interestRate)
				&& Objects.equals(lender, other.lender) && Objects.equals(loanAmount, other.loanAmount)
				&& status == other.status;
	}

	@Override
	public String toString() {
		return "Loan [id=" + id + ", borrower=" + borrower + ", lender=" + lender + ", loanAmount=" + loanAmount
				+ ", interestRate=" + interestRate + ", dateLent=" + dateLent + ", dateDue=" + dateDue
				+ ", amountRepayed=" + amountRepayed + "]";
	}

}
