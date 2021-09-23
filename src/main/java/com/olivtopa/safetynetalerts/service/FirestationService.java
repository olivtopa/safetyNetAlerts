package com.olivtopa.safetynetalerts.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.olivtopa.safetynetalerts.dao.PersonDAO;
import com.olivtopa.safetynetalerts.dao.FireStationDAO;
import com.olivtopa.safetynetalerts.dao.MedicalRecordDAO;
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

	public FirestationService(PersonDAO personDAO, FireStationDAO fireStationDAO, MedicalRecordDAO medicalRecordDAO) {
		this.personDAO = personDAO;
		this.fireStationDAO = fireStationDAO;
		this.medicalRecordDAO = medicalRecordDAO;
	}

	public PersonsInFireStation findPersonsInFireStationScope(long stationNumber) {

		Set<String> addressOfFireStations = fireStationDAO.getAll().stream()
				.filter(firesStation -> firesStation.getStation() == stationNumber) // on filtre sur la station demandée
				.map(FiresStation::getAddress) // on extrait le champ qui nous intéresse
				.collect(Collectors.toSet()); // on stocke dans une structure qui permet de savoir rapidement si une
												// adresse est connue

		List<Person> allPersons = personDAO.getAll().stream()
				.filter(person -> addressOfFireStations.contains(person.getAddress())) // la méthode contains permet de
																						// savoir si l'adresse de la
																						// personne est dans toutes les
																						// adresses dans le périmètre de
																						// la station
				.collect(Collectors.toList());

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

		return personsInFireStation;
	}

	private int countChildren(List<MedicalRecord> medicalRecords) {
		return (int) medicalRecords.stream().map(MedicalRecord::getBirthdate).map(this::computeAge)
				.filter(age -> age < 18).count();
	}

	private int countAdults(List<MedicalRecord> medicalRecords) {
		return (int) medicalRecords.stream().map(MedicalRecord::getBirthdate).map(this::computeAge)
				.filter(age -> age >= 18).count();
	}

	private int computeAge(LocalDate localDate) {
		return Period.between(localDate, LocalDate.now()).getYears();
	}
	
	public List<FiresStation> getAll(){
		return fireStationDAO.getAll();
	}

	public List<FiresStation> create(FiresStation newFiresStation) {

		List<FiresStation> firesStation = getAll();
		
		firesStation.add(newFiresStation);

		return firesStation;

	}
}
