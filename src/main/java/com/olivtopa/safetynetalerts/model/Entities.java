package com.olivtopa.safetynetalerts.model;

import java.util.List;

public class Entities {
	private List<Person> Persons;
	List<FiresStation> Firestations;
	List<MedicalRecord> Medicalrecords;
	public List<Person> getPersons() {
		return Persons;
	}
	public void setPersons(List<Person> persons) {
		Persons = persons;
	}
	public List<FiresStation> getFirestations() {
		return Firestations;
	}
	public void setFirestations(List<FiresStation> firestations) {
		Firestations = firestations;
	}
	public List<MedicalRecord> getMedicalrecords() {
		return Medicalrecords;
	}
	public void setMedicalrecords(List<MedicalRecord> medicalrecords) {
		Medicalrecords = medicalrecords;
	}

	
}
