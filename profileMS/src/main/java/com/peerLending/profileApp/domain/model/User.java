package com.peerLending.profileApp.domain.model;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
	
	@Id
	private String userName;
	private String firstName;
	private String lastName;
	private int age;
	private String occupation;
	private LocalDate registeredSince;
	/**
	 * Default constructor.
	 */
	public User() {
		super();
		this.registeredSince = LocalDate.now();
	}
	/**
	 * @param userName
	 * @param firstName
	 * @param lastName
	 * @param age
	 * @param occupation
	 * @param registeredSince
	 */
	public User(String userName, String firstName, String lastName, int age, String occupation
			) {
		super();
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.occupation = occupation;
		this.registeredSince = LocalDate.now();
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}
	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}
	/**
	 * @return the occupation
	 */
	public String getOccupation() {
		return occupation;
	}
	/**
	 * @param occupation the occupation to set
	 */
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	/**
	 * @return the registeredSince
	 */
	public LocalDate getRegisteredSince() {
		return registeredSince;
	}
	@Override
	public int hashCode() {
		return Objects.hash(age, firstName, lastName, occupation, registeredSince, userName);
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
				&& Objects.equals(lastName, other.lastName) && Objects.equals(occupation, other.occupation)
				&& Objects.equals(registeredSince, other.registeredSince) && Objects.equals(userName, other.userName);
	}
	@Override
	public String toString() {
		return "User [userName=" + userName + ", firstName=" + firstName + ", lastName=" + lastName + ", age=" + age
				+ ", occupation=" + occupation + ", registeredSince=" + registeredSince + "]";
	}

	

}
