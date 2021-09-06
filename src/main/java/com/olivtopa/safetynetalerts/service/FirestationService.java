package com.olivtopa.safetynetalerts.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.olivtopa.safetynetalerts.dao.PersonDAO;
import com.olivtopa.safetynetalerts.model.Person;
import com.olivtopa.safetynetalerts.model.PersonsInFireStation;

@Service
public class FirestationService {
	
	private final PersonDAO personDAO;
	
	public FirestationService(PersonDAO personDAO) {
		this.personDAO = personDAO;
	}

	public PersonsInFireStation findPersonsInFireStationScope(long stationNumber) {

	    PersonsInFireStation personsInFireStation = new PersonsInFireStation();

	    List<Person> allPersons = personDAO.getAll();


	    List<Object> persons = new ArrayList<>();
	    allPersons.forEach(onePerson -> persons.add(onePerson));

	    personsInFireStation.setPersons(persons);

	    return personsInFireStation;
	}

}
