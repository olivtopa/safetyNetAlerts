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

	public void create(Person newPerson) {
		personDAO.create(newPerson);

	}

	public void update(Person newPerson) {
		personDAO.update(newPerson);

	}

	public void delete(String firstName, String lastName){
		personDAO.delete(firstName, lastName);
		
	}

}
