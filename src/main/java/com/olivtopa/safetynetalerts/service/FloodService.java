package com.olivtopa.safetynetalerts.service;

import java.time.LocalDate;
import java.time.Period;

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

@Service
public class FloodService {

	@Autowired
	private FireStationDAO fireStationDAO;

	@Autowired
	private PersonDAO personDAO;

	@Autowired
	private MedicalRecordDAO medicalRecordDAO;

	private FloodAddress buildFloodAddress(List<FloodPerson> person, String fireStationAddresses) {

		FloodAddress floodAddress = new FloodAddress();

		floodAddress.setAddress(fireStationAddresses);
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

	public FloodAddress finalFloodList(List<Integer> stations) {
		

		stations.forEach(station -> foyerByFireStationNumber(station));
		
		return buildFloodAddress(floodPersonListByAddress(foyerByFireStationNumber(station)),
				foyerByFireStationNumber(station));

	}

	public String foyerByFireStationNumber(int fireStationNumber) {

		String fireStationAddresses = fireStationDAO.getAll().stream().filter(s -> s.getStation() == fireStationNumber)
				.map(FiresStation::getAddress).findAny().orElseThrow();

		return fireStationAddresses;

	}

	

	public List<FloodPerson> floodPersonListByAddress(String fireStationAddresses) {
		List<FloodPerson> floodPerson = personDAO.getAll().stream()
				.filter(a -> a.getAddress().equals(fireStationAddresses))
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
