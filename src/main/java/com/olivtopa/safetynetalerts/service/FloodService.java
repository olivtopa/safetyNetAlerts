package com.olivtopa.safetynetalerts.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olivtopa.safetynetalerts.dao.MedicalRecordDAO;
import com.olivtopa.safetynetalerts.dao.PersonDAO;
import com.olivtopa.safetynetalerts.model.FloodFoyer;
import com.olivtopa.safetynetalerts.model.MedicalRecord;
import com.olivtopa.safetynetalerts.model.Person;

@Service
public class FloodService {

	@Autowired
	private PersonDAO personDAO;

	@Autowired
	private MedicalRecordDAO medicalRecordDAO;

	private FloodFoyer buildFloodFoyer(Person person, MedicalRecord medicalRecord) {

		FloodFoyer floodFoyer = new FloodFoyer();

		floodFoyer.setFirstName(person.getFirstName());
		floodFoyer.setLastName(person.getLastName());
		floodFoyer.setAddress(person.getAddress());
		floodFoyer.setPhone(person.getPhone());
		floodFoyer.setMedications(medicalRecord.getMedications());
		floodFoyer.setAllergies(medicalRecord.getAllergies());

		int age;
		LocalDate birthdate = medicalRecord.getBirthdate();
		LocalDate currentdate = LocalDate.now();

		age = calculateAge(birthdate, currentdate);

		floodFoyer.setAge(age);

		return floodFoyer;

	}

	public List<FloodFoyer> floodPersonListByAddress(String address) {
		List<FloodFoyer> foyer = personDAO.getAll().stream().filter(a -> a.getAddress().equals(address)).map(
				person -> buildFloodFoyer(person, findMedicalRecord(person.getFirstName(), person.getLastName())))
				.collect(Collectors.toList());
		return foyer;
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
