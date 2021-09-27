package com.olivtopa.safetynetalerts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olivtopa.safetynetalerts.dao.PersonDAO;
import com.olivtopa.safetynetalerts.model.Person;

@Service
public class PersonService {

	@Autowired
	private PersonDAO personDAO;

	public List<Person> getAll() {
		return personDAO.getAll();
	}

	public List<Person> create(Person newPerson) {
		List<Person> person = getAll();
		person.add(newPerson);
		personDAO.create(newPerson);
		return person;
	}

}
