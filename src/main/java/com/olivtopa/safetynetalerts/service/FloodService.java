package com.olivtopa.safetynetalerts.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olivtopa.safetynetalerts.dao.FireStationDAO;
import com.olivtopa.safetynetalerts.dao.MedicalRecordDAO;
import com.olivtopa.safetynetalerts.dao.PersonDAO;
import com.olivtopa.safetynetalerts.model.FiresStation;
import com.olivtopa.safetynetalerts.model.FloodAddress;
import com.olivtopa.safetynetalerts.model.FloodPerson;
import com.olivtopa.safetynetalerts.model.MedicalRecord;
import com.olivtopa.safetynetalerts.model.Person;
import com.olivtopa.safetynetalerts.model.PersonList;

@Service
public class FloodService {

	@Autowired
	private FireStationDAO fireStationDAO;

	@Autowired
	private PersonDAO personDAO;

	@Autowired
	private MedicalRecordDAO medicalRecordDAO;

	private FloodAddress buildFloodAddress(Person person, String fireStationAddresses) {

		FloodAddress floodAddress = new FloodAddress();
		floodAddress.setAddress(person.getAddress());
		floodAddress.setFloodPerson(floodPersonListByAddress(fireStationAddresses));

		return floodAddress;
	}

	private FloodPerson buildFloodPerson(Person person, MedicalRecord medicalRecord) {

		FloodPerson floodPerson = new FloodPerson();

		floodPerson.setFirstName(person.getFirstName());
		floodPerson.setLastName(person.getLastName());
		floodPerson.setAddress(person.getAddress());
		floodPerson.setPhone(person.getPhone());
		floodPerson.setAllergies(null);
		floodPerson.setMedications(null);

		int age;
		LocalDate birthdate = medicalRecord.getBirthdate();
		LocalDate currentdate = LocalDate.now();

		age = calculateAge(birthdate, currentdate);

		floodPerson.setAge(age);

		return floodPerson;

	}

	public List<String> foyerByFireStationNumber(int fireStationNumber) {

		List<String> fireStationAddresses = fireStationDAO.getAll().stream()
				.filter(s -> s.getStation() == (fireStationNumber)).map(FiresStation::getAddress)
				.collect(Collectors.toList());

		return fireStationAddresses;

	}

	public List<FloodAddress> buildAddressObject(Person person, String fireStationAddresses) {

		List<FloodAddress> floodAddress = personDAO.getAll().stream()
				.map(p -> buildFloodAddress(p, fireStationAddresses)).collect(Collectors.toList());

		return floodAddress;

	}

	public List<FloodPerson> floodPersonListByAddress(String fireStationAddresses) {
		List<FloodPerson> floodPerson = personDAO.getAll().stream().filter(a -> a.getAddress().equals(fireStationAddresses))
				.map(person -> buildFloodPerson(person, findMedicalRecord(person.getFirstName(), person.getLastName())))
				.collect(Collectors.toList());
		return floodPerson;

	}

	private MedicalRecord findMedicalRecord(String firstName, String lastName) {
		MedicalRecord medical = medicalRecordDAO.getAll().stream()
				.filter(person -> person.getFirstName().equals(firstName) && person.getLastName().equals(lastName))
				.distinct().findAny().orElseThrow();
		return medical;
	}

	public int calculateAge(LocalDate birthdate, LocalDate currentDate) {
		return Period.between(birthdate, currentDate).getYears();
	}
}
