package com.peerLender.landingEngine.domain.model;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public final class User {

	@Id
	private String userName;
	private String firstName;
	private String lasttName;
	private int age;
	private String occupation;
	@OneToOne(cascade = { CascadeType.ALL })
	private Balance balance;

	public User() {
		super();
	}

	public User(String userName, String firstName, String lasttName, int age, String occupation, Balance balance) {
		super();
		this.userName = userName;
		this.firstName = firstName;
		this.lasttName = lasttName;
		this.age = age;
		this.occupation = occupation;
		this.balance = balance;
	}

	public void topUp(final Money money) {
		this.balance.topUp(money);
	}

	public void withDrawl(final Money money) {
		this.balance.withdraw(money);
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @return the lasttName
	 */
	public String getLasttName() {
		return lasttName;
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @return the occupation
	 */
	public String getOccupation() {
		return occupation;
	}

	/**
	 * @return the balance
	 */
	public Balance getBalance() {
		return balance;
	}

	/**
	 * @param balance the balance to set
	 */
	public void setBalance(Balance balance) {
		this.balance = balance;
	}

	@Override
	public int hashCode() {
		return Objects.hash(age, balance, firstName, lasttName, occupation, userName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return age == other.age && Objects.equals(balance, other.balance) && Objects.equals(firstName, other.firstName)
				&& Objects.equals(lasttName, other.lasttName) && Objects.equals(occupation, other.occupation)
				&& Objects.equals(userName, other.userName);
	}

	@Override
	public String toString() {
		return "User [userName=" + userName + ", firstName=" + firstName + ", lasttName=" + lasttName + ", age=" + age
				+ ", occupation=" + occupation + ", balance=" + balance + "]";
	}

}
