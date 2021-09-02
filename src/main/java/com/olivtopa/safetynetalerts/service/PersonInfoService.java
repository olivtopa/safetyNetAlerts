package com.olivtopa.safetynetalerts.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olivtopa.safetynetalerts.dao.PersonDAO;
import com.olivtopa.safetynetalerts.model.MedicalRecord;
import com.olivtopa.safetynetalerts.dao.MedicalRecordDAO;
import com.olivtopa.safetynetalerts.model.Person;
import com.olivtopa.safetynetalerts.model.PersonInfo;

@Service
public class PersonInfoService {

	@Autowired
	private PersonDAO personDAO;
	@Autowired
	private MedicalRecordDAO medicalRecordDAO;

	private PersonInfo buildPersonInfo(Person person, MedicalRecord medicalRecord) {

		PersonInfo personInfo = new PersonInfo();

		personInfo.setFirstName(person.getFirstName());
		personInfo.setLastName(person.getLastName());
		personInfo.setAddress(person.getAddress());
		personInfo.setEmail(person.getEmail());
		personInfo.setMedications(medicalRecord.getMedications());
		personInfo.setAllergies(medicalRecord.getAllergies());

		int age;
		LocalDate birthdate = medicalRecord.getBirthdate();
		LocalDate currentdate = LocalDate.now();

		CalculateAge calculAge = new CalculateAge();
		age = calculAge.calculateAge(birthdate, currentdate);

		personInfo.setAge(age);

		return personInfo;

	}

	public List<PersonInfo> personDetails(String firstName, String lastName) {

		List<PersonInfo> personInfo = personDAO.getAll().stream()
				.filter(person -> person.getFirstName().equals(firstName) && person.getLastName().equals(lastName))
				.map(person -> buildPersonInfo(person, findMedicalRecord(person.getFirstName(), person.getLastName())))
				.collect(Collectors.toList());
		return personInfo;

	}

	private MedicalRecord findMedicalRecord(String firstName, String lastName) {
		MedicalRecord medical = medicalRecordDAO.getAll().stream()
				.filter(person -> person.getFirstName().equals(firstName) && person.getLastName().equals(lastName)).findAny().orElse(null);
		return medical;

	}
}