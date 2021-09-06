package com.olivtopa.safetynetalerts.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.olivtopa.safetynetalerts.dao.PersonDAO;
import com.olivtopa.safetynetalerts.model.Person;
import com.olivtopa.safetynetalerts.model.PersonInFireStation;
import com.olivtopa.safetynetalerts.model.PersonsInFireStation;

@Service
public class FirestationService {
	
	private final PersonDAO personDAO;
	
	public FirestationService(PersonDAO personDAO) {
		this.personDAO = personDAO;
	}

	public PersonsInFireStation findPersonsInFireStationScope(long stationNumber) {
		
		List<Person> allPersons = personDAO.getAll();

	    PersonsInFireStation personsInFireStation = new PersonsInFireStation();

	    List<PersonInFireStation> persons = new ArrayList<>();
        allPersons.forEach(onePerson -> {
            PersonInFireStation personInFireStation = new PersonInFireStation();
            personInFireStation.setFirstName(onePerson.getFirstName());
            personInFireStation.setLastName(onePerson.getLastName());
            persons.add(personInFireStation);
        });

        personsInFireStation.setPersons(persons);

        return personsInFireStation;
	}

}
