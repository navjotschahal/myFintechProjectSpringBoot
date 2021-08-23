package com.peerLender.landingEngine.domain.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKeyClass;
import javax.persistence.OneToMany;

import com.peerLender.landingEngine.application.enums.Currency;

@Entity
public class Balance {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ElementCollection
	@MapKeyClass(Currency.class)
	@OneToMany(targetEntity = Money.class, cascade = CascadeType.ALL)
	private Map<Currency, Money> moneyMap = new HashMap<Currency, Money>();

	/**
	 * 
	 */
	public Balance() {
		super();
	}

	public void topUp(final Money money) {
		if (moneyMap.get(money.getCurrency()) == null) {
			moneyMap.put(money.getCurrency(), money);
		} else {
			moneyMap.put(money.getCurrency(), moneyMap.get(money.getCurrency()).add(money));
		}
	}
	
	public void withdraw(final Money money) {
		final Money moneyBalance = moneyMap.get(money.getCurrency());
		if (moneyBalance == null || moneyBalance.getAmount() <= 0) {
			throw new IllegalStateException();
		} else {
			moneyMap.put(money.getCurrency(), moneyBalance.minus(money));
		}
	}

	/**
	 * @return the moneyMap
	 */
	public Map<Currency, Money> getMoneyMap() {
		return moneyMap;
	}

	/**
	 * @param moneyMap the moneyMap to set
	 */
	public void setMoneyMap(Map<Currency, Money> moneyMap) {
		this.moneyMap = moneyMap;
	}

	@Override
	public int hashCode() {
		return Objects.hash(moneyMap);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Balance other = (Balance) obj;
		return Objects.equals(moneyMap, other.moneyMap);
	}

}
