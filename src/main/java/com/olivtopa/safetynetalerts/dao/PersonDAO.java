package com.olivtopa.safetynetalerts.dao;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.olivtopa.safetynetalerts.constant.File;
import com.olivtopa.safetynetalerts.controller.EntitiesLoader;
import com.olivtopa.safetynetalerts.model.Entities;
import com.olivtopa.safetynetalerts.model.Person;

@Repository
public class PersonDAO {

	public List<Person> getAll() {
		try {
			EntitiesLoader entitiesLoader = new EntitiesLoader();
			return entitiesLoader.load(File.FILENAME).getPersons();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void create(Person person) {
		try {
			EntitiesLoader entitiesLoader = new EntitiesLoader();
			Entities entities = entitiesLoader.load(File.FILENAME);
			entities.getPersons().add(person);

			entitiesLoader.write(File.FILENAME, entities);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}
	
	public void update(Person person) {
		try {
			EntitiesLoader entitiesLoader = new EntitiesLoader();
			Entities entities = entitiesLoader.load(File.FILENAME);
			entities.getPersons().stream().filter(p->p.getFirstName().
					equals(person.getFirstName())&& person.getLastName().equals(person.getLastName())).collect(Collectors.toList());
			entities.getPersons().add(person);
			entitiesLoader.write(File.FILENAME, entities);
		}catch (IOException e) {
			throw new RuntimeException(e);
	}

}
}
