package com.olivtopa.safetynetalerts.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Fire {

	@JsonIgnore
	private FiresStation firesStation;
	@JsonIgnore
	private Person person;
	@JsonIgnore
	private MedicalRecord medicalRecord;

	private String firstName;
	private String lastName;
	private int station;
	private List<String> medications;
	private List<String> allergies;
	private int age;
	private String Phone;

	public String getPhone() {
		return Phone;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;

	}

	public FiresStation getFiresStation() {
		return firesStation;
	}

	public Person getPerson() {
		return person;
	}

	public MedicalRecord getMedicalRecord() {
		return medicalRecord;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public int getStation() {
		return station;
	}

	public List<String> getMedications() {
		return medications;
	}

	public List<String> getAllergies() {
		return allergies;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;

	}

	public void setStation(int station) {
		this.station = station;

	}

	public void setMedications(List<String> medications) {
		this.medications = medications;

	}

	public void setAllergies(List<String> allergies) {
		this.allergies = allergies;

	}

	public void setAge(int age) {
		this.age = age;

	}

	public int getAge() {
		return age;
	}

	public void setPhone(String phone) {
		this.Phone= phone;
		
	}

	@Override
	public String toString() {
		return "Fire [firesStation=" + firesStation + ", person=" + person + ", medicalRecord=" + medicalRecord
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", station=" + station + ", medications="
				+ medications + ", allergies=" + allergies + ", age=" + age + ", Phone=" + Phone + "]";
	}

}
