package com.olivtopa.safetynetalerts.model;

import java.util.List;

public class Entities {
	private List<Person> Persons;
	List<FiresStation> FireStations;
	List<MedicalRecord> MedicalRecords;

	public List<Person> getPersons() {
		return Persons;
	}

	public void setPersons(List<Person> persons) {
		Persons = persons;
	}

}
