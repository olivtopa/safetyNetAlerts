package com.olivtopa.safetynetalerts.DAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.olivtopa.safetynetalerts.controller.EntitiesLoader;
import com.olivtopa.safetynetalerts.dao.PersonDAO;
import com.olivtopa.safetynetalerts.model.Entities;
import com.olivtopa.safetynetalerts.model.Person;

@ExtendWith(MockitoExtension.class)
class PersonDAOTest {

	@InjectMocks
	PersonDAO personDAO;

	@Mock
	EntitiesLoader entitiesLoader;

	@Test
	public void create() throws IOException {

		// Given
		Entities entities = new Entities();
		entities.setPersons(new ArrayList<>());
		Mockito.when(entitiesLoader.load(ArgumentMatchers.anyString())).thenReturn(entities);

		// When
		Person personUpdated = new Person();
		personUpdated.setFirstName("Oliv");
		personUpdated.setLastName("Topa");
		personUpdated.setPhone("0102030405");

		personDAO.create(personUpdated);

		// Then
		Assertions.assertThat(entities.getPersons()).first().usingRecursiveComparison().isEqualTo(personUpdated);
	}

	@Test
	public void update() throws IOException {

		// Given
		Entities entities = new Entities();
		Person person = new Person();
		person.setFirstName("Oliv");
		person.setLastName("Topa");
		person.setPhone("0000000000");
		List<Person> persons = new ArrayList<>();
		persons.add(person);
		entities.setPersons(persons);
		Mockito.when(entitiesLoader.load(ArgumentMatchers.anyString())).thenReturn(entities);

		// When
		Person personUpdated = new Person();
		personUpdated.setFirstName(person.getFirstName());
		personUpdated.setLastName(person.getLastName());
		personUpdated.setPhone("0102030405");

		personDAO.update(personUpdated);

		// Then
		Assertions.assertThat(entities.getPersons()).first().extracting(Person::getPhone)
				.isEqualTo(personUpdated.getPhone());
	}
	
	@Test
	public void delete() throws IOException {
		
		//GIVEN
		Entities entities = new Entities();
		Person person = new Person();
		person.setFirstName("Oliv");
		person.setLastName("Topa");
		person.setPhone("0000000000");
		List<Person> persons = new ArrayList<>();
		persons.add(person);
		entities.setPersons(persons);
		Mockito.when(entitiesLoader.load(ArgumentMatchers.anyString())).thenReturn(entities);
		
		//WHEN
		Person personDeleted = new Person();
		personDeleted.setFirstName(person.getFirstName());
		personDeleted.setLastName(person.getLastName());
		
		personDAO.delete(personDeleted);
		
		//THEN
		Assertions.assertThat(entities.getPersons().isEmpty());
	}
}