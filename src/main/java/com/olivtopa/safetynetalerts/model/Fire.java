package com.olivtopa.safetynetalerts.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class Fire {
	

	@JsonIgnore
	private FiresStation firesStation;
	@JsonIgnore
	private Person person;
	@JsonIgnore
	private MedicalRecord medicalRecord;

	@JsonFormat(pattern = "MM/dd/yyyy")
	private LocalDate age;

	private String firstName;
	private String lastName;
	private int station;
	private List<String> medications;
	private List<String> allergies;

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

	public LocalDate getAge() {
		return age;
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

	
}
