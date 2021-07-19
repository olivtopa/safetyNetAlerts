package com.olivtopa.safetynetalerts.dao;

import java.util.List;

import com.olivtopa.safetynetalerts.constant.File;
import com.olivtopa.safetynetalerts.controller.EntitiesLoader;
import com.olivtopa.safetynetalerts.model.Entities;
import com.olivtopa.safetynetalerts.model.Person;

public class PersonDAO {

	Entities entities = EntitiesLoader.load(File.FILENAME);
	List<Person> persons = entities.getPersons();

}
