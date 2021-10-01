package com.olivtopa.safetynetalerts.dao;

import java.io.IOException;
import java.util.List;

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
		} catch (IOException e) {
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
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	public void delete(Person newPerson) {
		try {
			Entities entities = entitiesLoader.load(File.FILENAME);
			entities.getPersons().removeIf(p -> p.getFirstName().equals(newPerson.getFirstName())
					&& p.getLastName().equals(newPerson.getLastName()));
			entitiesLoader.write(File.FILENAME, entities);
		} catch (IOException e) {
			throw new RuntimeException(e);

		}
	}
}