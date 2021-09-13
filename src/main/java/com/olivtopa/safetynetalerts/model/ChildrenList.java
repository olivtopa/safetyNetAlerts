package com.olivtopa.safetynetalerts.model;

import java.util.List;

public class ChildrenList {

	private String firstName;
	private String lastName;
	private String address;
	private String phone;
	private int age;
	private List<PersonList> personList;

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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public List<PersonList> getPersonList() {
		return personList;
	}

	public void setPersonList(List<PersonList> personList) {
		this.personList = personList;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
