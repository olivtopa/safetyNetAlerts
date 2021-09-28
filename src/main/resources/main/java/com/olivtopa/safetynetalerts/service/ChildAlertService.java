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
import com.olivtopa.safetynetalerts.model.ChildrenList;
import com.olivtopa.safetynetalerts.model.MedicalRecord;
import com.olivtopa.safetynetalerts.model.Person;

@Service
public class ChildAlertService {

	@Autowired
	private PersonDAO personDAO;
	
	@Autowired
	private MedicalRecordDAO medicalRecordDAO;
	
	
	private PersonList buildPersonList(Person person, MedicalRecord medicalRecord) {

		PersonList personList = new PersonList();

		personList.setFirstName(person.getFirstName());
		personList.setLastName(person.getLastName());
		personList.setAddress(person.getAddress());
		personList.setPhone(person.getPhone());

		int age;
		LocalDate birthdate = medicalRecord.getBirthdate();
		LocalDate currentdate = LocalDate.now();

		age = calculateAge(birthdate, currentdate);

		personList.setAge(age);
		

		return personList;

	}
	
	private ChildrenList buildChildrenList(PersonList person, MedicalRecord medicalRecord, String address) {

		ChildrenList childrenList = new ChildrenList();

		childrenList.setFirstName(person.getFirstName());
		childrenList.setLastName(person.getLastName());
		childrenList.setAddress(person.getAddress());
		childrenList.setPhone(person.getPhone());
		childrenList.setAge(person.getAge());
		childrenList.setPersonList(personListByAddress(address));
		//childrenList.setPersonList(personListByAddress(address).stream().filter(p -> p.getAge() >= 18).collect(Collectors.toList()));

		return childrenList;

	}
	
	

	public List<PersonList> personListByAddress(String address) {
		List<PersonList> foyer = personDAO.getAll().stream().filter(a -> a.getAddress().equals(address)).map(
				person -> buildPersonList(person, findMedicalRecord(person.getFirstName(), person.getLastName())))
				.collect(Collectors.toList());
		return foyer;
	}

	private MedicalRecord findMedicalRecord(String firstName, String lastName) {
		MedicalRecord medical = medicalRecordDAO.getAll().stream()
				.filter(person -> person.getFirstName().equals(firstName) && person.getLastName().equals(lastName))
				.distinct().findAny().orElseThrow();
		return medical;
	}
	
	public List<ChildrenList> childrenList(String address){
		
		List<ChildrenList> children = personListByAddress(address).stream().filter(a -> a.getAge() <= 18)
				.map(person -> buildChildrenList(person, findMedicalRecord(person.getFirstName(), person.getLastName()),address))
				.collect(Collectors.toList());
		return children;
		
		
	}

	public int calculateAge(LocalDate birthdate, LocalDate currentDate) {
		return Period.between(birthdate, currentDate).getYears();
	}
}
