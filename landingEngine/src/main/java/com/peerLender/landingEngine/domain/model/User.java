package com.peerLender.landingEngine.domain.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public final class User {
	
	@Id
	private String userName;
	private String firstName;
	private String lasttName;
	private int age;
	private String occupation;
	
	public User() {
		super();
	}

	public User(String userName, String firstName, String lasttName, int age, String occupation) {
		super();
		this.userName = userName;
		this.firstName = firstName;
		this.lasttName = lasttName;
		this.age = age;
		this.occupation = occupation;
	}

	public User(String firstName, String lasttName, int age, String occupation) {
		super();
		this.firstName = firstName;
		this.lasttName = lasttName;
		this.age = age;
		this.occupation = occupation;
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

	@Override
	public int hashCode() {
		return Objects.hash(age, firstName, lasttName, occupation);
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
		return age == other.age && Objects.equals(firstName, other.firstName)
				&& Objects.equals(lasttName, other.lasttName) && Objects.equals(occupation, other.occupation);
	}

	@Override
	public String toString() {
		return "User [firstName=" + firstName + ", lasttName=" + lasttName + ", age=" + age + ", occupation="
				+ occupation + "]";
	}

}
