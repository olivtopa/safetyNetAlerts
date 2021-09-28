package com.olivtopa.safetynetalerts.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olivtopa.safetynetalerts.dao.MedicalRecordDAO;
import com.olivtopa.safetynetalerts.model.MedicalRecord;
import com.olivtopa.safetynetalerts.dao.FireStationDAO;
import com.olivtopa.safetynetalerts.dao.PersonDAO;
import com.olivtopa.safetynetalerts.model.Fire;
import com.olivtopa.safetynetalerts.model.Person;
import com.olivtopa.safetynetalerts.model.FiresStation;

@Service
public class FireService {

	@Autowired
	private MedicalRecordDAO medicalRecordDAO;
	@Autowired
	private FireStationDAO fireStationDAO;
	@Autowired
	private PersonDAO personDAO;

	FiresStation firesStation;

	private Fire buildFire(Person person, FiresStation firesStation, MedicalRecord medicalRecord) {

		Fire fire = new Fire();

		fire.setFirstName(person.getFirstName());
		fire.setLastName(person.getLastName());
		fire.setStation(firesStation.getStation());
		fire.setMedications(medicalRecord.getMedications());
		fire.setAllergies(medicalRecord.getAllergies());
		fire.setPhone(person.getPhone());

		int age;
		LocalDate birthdate = medicalRecord.getBirthdate();
		LocalDate currentdate = LocalDate.now();

		age = calculateAge(birthdate, currentdate);

		fire.setAge(age);

		return fire;

	}

	public List<Fire> inhabitantByAddress(String address) {

		List<Fire> fire = personDAO
				.getAll().stream().filter(a -> a.getAddress().equals(address)).map(person -> buildFire(person,
						findFireStation(address), findMedicalRecord(person.getFirstName(), person.getLastName())))
				.collect(Collectors.toList());
		return fire;

	}

	private FiresStation findFireStation(String address) {

		FiresStation stations = fireStationDAO.getAll().stream()
				.filter(fireStation -> fireStation.getAddress().equals(address)).distinct().findAny().orElse(null);
		return stations;
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