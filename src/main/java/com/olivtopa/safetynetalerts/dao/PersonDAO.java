package com.olivtopa.safetynetalerts.dao;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.olivtopa.safetynetalerts.constant.File;
import com.olivtopa.safetynetalerts.controller.EntitiesLoader;
import com.olivtopa.safetynetalerts.model.Entities;
import com.olivtopa.safetynetalerts.model.Person;



@Repository
public class PersonDAO {

	@Autowired
	private EntitiesLoader entitiesLoader;

	private static Logger logger = LoggerFactory.getLogger(PersonDAO.class);
	
	public List<Person> getAll() {
		try {
			return entitiesLoader.load(File.FILENAME).getPersons();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void create(Person person) {
		try {
			Entities entities = entitiesLoader.load(File.FILENAME);
			entities.getPersons().add(person);

			entitiesLoader.write(File.FILENAME, entities);
			logger.info("{} is correctly added to the Person list",person.toString());
		} catch (IOException e) {
			logger.error("A problem occurred while creating a person",e);
			throw new RuntimeException(e);
		}

	}

	public void update(Person newPerson) {
		try {
			Entities entities = entitiesLoader.load(File.FILENAME);
			entities.getPersons().removeIf(p -> p.getFirstName().equals(newPerson.getFirstName())
					&& p.getLastName().equals(newPerson.getLastName()));
			entities.getPersons().add(newPerson);
			entitiesLoader.write(File.FILENAME, entities);
			logger.info("{} {} is correctly modified in the Person list",newPerson.getFirstName(),newPerson.getLastName());
		} catch (IOException e) {
			logger.error("A problem occurred while modify a person",e);
			throw new RuntimeException(e);
		}

	}

	public void delete(String firstName, String lastName) {
		try {
			Entities entities = entitiesLoader.load(File.FILENAME);
			entities.getPersons().removeIf(p -> p.getFirstName().equals(firstName)
					&& p.getLastName().equals(lastName));
			entitiesLoader.write(File.FILENAME, entities);
			logger.info("{} {} is correctly deleted in the Person list",firstName,lastName);
		} catch (IOException e) {
			logger.error("A problem occurred while deleting a person");
			throw new RuntimeException(e);

		}
	}
}