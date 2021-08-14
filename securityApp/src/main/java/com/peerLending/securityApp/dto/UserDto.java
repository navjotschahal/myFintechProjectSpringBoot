package com.peerLending.securityApp.dto;

import java.util.Objects;

public class UserDto {

	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private String occupation;
	private int age;


	/**
	 * 
	 */
	public UserDto() {
		super();
	}


	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}


	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}


	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}


	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}


	/**
	 * @return the occupation
	 */
	public String getOccupation() {
		return occupation;
	}


	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}


	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
