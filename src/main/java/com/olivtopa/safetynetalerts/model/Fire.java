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

	int station = firesStation.getStation();
	String lastName = person.getLastName();
	String phone = person.getPhone();
	List<String> allergies = medicalRecord.getAllergies();
	
}
