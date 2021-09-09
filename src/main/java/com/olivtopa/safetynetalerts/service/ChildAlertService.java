package com.olivtopa.safetynetalerts.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olivtopa.safetynetalerts.dao.MedicalRecordDAO;
import com.olivtopa.safetynetalerts.dao.PersonDAO;
import com.olivtopa.safetynetalerts.model.PersonList;

import com.olivtopa.safetynetalerts.model.MedicalRecord;
import com.olivtopa.safetynetalerts.model.Person;

@Service
public class ChildAlertService {

	@Autowired
	private PersonDAO personDAO;
	
	@Autowired
	private MedicalRecordDAO medicalRecordDAO;
	
	
	private PersonList buildChildrenList(Person person, MedicalRecord medicalRecord) {

		PersonList childrenList = new PersonList();

		childrenList.setFirstName(person.getFirstName());
		childrenList.setLastName(person.getLastName());

		int age;
		LocalDate birthdate = medicalRecord.getBirthdate();
		LocalDate currentdate = LocalDate.now();

		age = calculateAge(birthdate, currentdate);

		childrenList.setAge(age);

		return childrenList;

	}

	public List<PersonList> finalChildrenList(String address) {
		List<PersonList> foyer = personDAO.getAll().stream().filter(a -> a.getAddress().equals(address)).map(
				person -> buildChildrenList(person, findMedicalRecord(person.getFirstName(), person.getLastName())))
				.collect(Collectors.toList());
		return foyer;
	}

	private MedicalRecord findMedicalRecord(String firstName, String lastName) {
		MedicalRecord medical = medicalRecordDAO.getAll().stream()
				.filter(person -> person.getFirstName().equals(firstName) && person.getLastName().equals(lastName))
				.distinct().findAny().orElse(null);
		return medical;
	}

	public int calculateAge(LocalDate birthdate, LocalDate currentDate) {
		return Period.between(birthdate, currentDate).getYears();
	}
}
