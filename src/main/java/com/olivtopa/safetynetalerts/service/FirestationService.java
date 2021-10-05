package com.olivtopa.safetynetalerts.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.olivtopa.safetynetalerts.dao.FireStationDAO;
import com.olivtopa.safetynetalerts.dao.MedicalRecordDAO;
import com.olivtopa.safetynetalerts.dao.PersonDAO;
import com.olivtopa.safetynetalerts.model.FiresStation;
import com.olivtopa.safetynetalerts.model.MedicalRecord;
import com.olivtopa.safetynetalerts.model.Person;
import com.olivtopa.safetynetalerts.model.PersonInFireStation;
import com.olivtopa.safetynetalerts.model.PersonsInFireStation;

@Service
public class FirestationService {

	private final PersonDAO personDAO;
	private final FireStationDAO fireStationDAO;
	private final MedicalRecordDAO medicalRecordDAO;

	private static Logger logger = LoggerFactory.getLogger(FirestationService.class);

	public FirestationService(PersonDAO personDAO, FireStationDAO fireStationDAO, MedicalRecordDAO medicalRecordDAO) {
		this.personDAO = personDAO;
		this.fireStationDAO = fireStationDAO;
		this.medicalRecordDAO = medicalRecordDAO;
	}

	public PersonsInFireStation findPersonsInFireStationScope(long stationNumber) {

		logger.info("Addresses based on station number {}", stationNumber);
		Set<String> addressOfFireStations = fireStationDAO.getAll().stream()
				.filter(firesStation -> firesStation.getStation() == stationNumber).map(FiresStation::getAddress)
				.collect(Collectors.toSet());

		List<Person> allPersons = personDAO.getAll().stream()
				.filter(person -> addressOfFireStations.contains(person.getAddress()))

				.collect(Collectors.toList());
		logger.info("search for people living at this address : {}", addressOfFireStations);
		return buildResult(allPersons, medicalRecordDAO.getAll());
	}

	private PersonsInFireStation buildResult(List<Person> allPersons, List<MedicalRecord> allMedicalRecords) {
		PersonsInFireStation personsInFireStation = new PersonsInFireStation();
		
		

		List<PersonInFireStation> persons = new ArrayList<>();
		List<MedicalRecord> medicalRecords = new ArrayList<>();
		allPersons.forEach(onePerson -> {
			MedicalRecord medicalRecordForPerson = allMedicalRecords.stream()
					.filter(medicalRecord -> onePerson.getFirstName().equals(medicalRecord.getFirstName())
							&& onePerson.getLastName().equals(medicalRecord.getLastName()))
					.findFirst().orElseThrow();
			medicalRecords.add(medicalRecordForPerson);
			

			PersonInFireStation personInFireStation = new PersonInFireStation();
			personInFireStation.setFirstName(onePerson.getFirstName());
			personInFireStation.setLastName(onePerson.getLastName());
			personInFireStation.setBirthDate(medicalRecordForPerson.getBirthdate());
			personInFireStation.setAddress(onePerson.getAddress());
			personInFireStation.setPhone(onePerson.getPhone());

			persons.add(personInFireStation);
		});

		personsInFireStation.setNbAdults(countAdults(medicalRecords));
		personsInFireStation.setNbChildren(countChildren(medicalRecords));

		personsInFireStation.setPersons(persons);
		logger.info("people found for this station number : {}",(personsInFireStation.getNbAdults()+personsInFireStation.getNbChildren()));
		return personsInFireStation;
	}

	private int countChildren(List<MedicalRecord> medicalRecords) {
		
		int nbChildren = (int) medicalRecords.stream().map(MedicalRecord::getBirthdate).map(this::computeAge)
				.filter(age -> age < 18).count();
		logger.info("Number of children int this station : {}", nbChildren);
		return nbChildren;
	}

	private int countAdults(List<MedicalRecord> medicalRecords) {
		int nbAdult = (int) medicalRecords.stream().map(MedicalRecord::getBirthdate).map(this::computeAge)
				.filter(age -> age >= 18).count();
		logger.info("Number of adult in this station : {}", nbAdult);
		return nbAdult;
	}

	private int computeAge(LocalDate localDate) {
		return Period.between(localDate, LocalDate.now()).getYears();
	}

	public void create(FiresStation newFiresStation) {
		fireStationDAO.create(newFiresStation);

	}

	public void update(FiresStation newFiresStation) {
		fireStationDAO.update(newFiresStation);
	}

	public void delete(String address, Integer station) {
		fireStationDAO.delete(address, station);
	}
}
