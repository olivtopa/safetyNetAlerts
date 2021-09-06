package com.olivtopa.safetynetalerts.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.olivtopa.safetynetalerts.dao.PersonDAO;
import com.olivtopa.safetynetalerts.dao.FireStationDAO;
import com.olivtopa.safetynetalerts.model.FiresStation;
import com.olivtopa.safetynetalerts.model.Person;
import com.olivtopa.safetynetalerts.model.PersonInFireStation;
import com.olivtopa.safetynetalerts.model.PersonsInFireStation;

@Service
public class FirestationService {
	
	private final PersonDAO personDAO;
	private final FireStationDAO fireStationDAO;
	
	public FirestationService(PersonDAO personDAO, FireStationDAO fireStationDAO) {
		this.personDAO = personDAO;
		this.fireStationDAO = fireStationDAO;
	}



public PersonsInFireStation findPersonsInFireStationScope(long stationNumber) {

    FiresStation fireStation = fireStationDAO.getAll().stream()
            .filter(firesStation -> firesStation.getStation() == stationNumber)
            .findFirst().orElseThrow();

    List<Person> allPersons = personDAO.getAll().stream().filter(person -> person.getAddress().equals(fireStation.getAddress()))
            .collect(Collectors.toList());

    return buildResult(allPersons);
}



	private PersonsInFireStation buildResult(List<Person> allPersons) {
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
