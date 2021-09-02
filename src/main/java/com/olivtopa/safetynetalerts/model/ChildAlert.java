package com.olivtopa.safetynetalerts.model;

import java.util.List;

public class ChildAlert {

	private String firstName;
	private String lastName;
	private String address;
	private List<String> foyer;
	private int age;

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<String> getFoyer() {
		return foyer;
	}

	public void setFoyer(List<String> foyer) {
		this.foyer = foyer;
	}

}
