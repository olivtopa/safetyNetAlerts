package com.olivtopa.safetynetalerts.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Fire {

	private FiresStation firesStation;
	private Person person;
	private MedicalRecord medicalRecord;

	@JsonFormat(pattern = "MM/dd/yyyy")
	private LocalDate age;

	private String firstName;
	private Object lastName;
	private int station;
	private List<String> medications;
	private List<String> allergies;

	public void setFirstName(String firstName) {
		this.firstName = firstName;

	}

	public void setLastName(String LastName) {
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
