package com.peerLender.landingEngine.domain.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.peerLender.landingEngine.application.enums.Currency;

@Entity
public final class Money {

	public static final Money ZERO = new Money(Currency.USD, 0);

	@Id
	@GeneratedValue // (strategy = GenerationType.IDENTITY)
	private long id;

	private Currency currency;
	private BigDecimal amount;

	/**
	 * 
	 */
	public Money() {
		super();
	}

	/**
	 * @param currency
	 * @param amount
	 */
	public Money(Currency currency, double amount) {
		super();
		this.currency = currency;
		this.amount = BigDecimal.valueOf(amount).setScale(2, RoundingMode.DOWN);
	}

	public Money times(double multiplier) {
		return new Money(Currency.USD, this.amount.doubleValue() * multiplier);
	}

	public Money add(final Money money) throws IllegalArgumentException {
		if (this.currency.equals(money.getCurrency())) {
			return new Money(this.currency, this.amount.doubleValue() + money.getAmount());
		}
		throw new IllegalArgumentException();
	}

	public Money minus(final Money money) throws IllegalArgumentException {
		if (this.currency.equals(money.getCurrency())) {
			return new Money(this.currency, this.amount.doubleValue() - money.getAmount());
		}
		throw new IllegalArgumentException();
	}

	/**
	 * @return the currency
	 */
	public Currency getCurrency() {
		return currency;
	}

	/**
	 * @return the amount
	 */
	public double getAmount() {
		return amount.doubleValue();
	}

	@Override
	public int hashCode() {
		return Objects.hash(amount, currency);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Money other = (Money) obj;
		return Objects.equals(amount, other.amount) && currency == other.currency;
	}

	@Override
	public String toString() {
		return "Money [currency=" + currency + ", amount=" + amount + "]";
	}

}
