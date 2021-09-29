package com.olivtopa.safetynetalerts.service;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.olivtopa.safetynetalerts.dao.PersonDAO;
import com.olivtopa.safetynetalerts.model.Person;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

	@InjectMocks
	private PersonService personService;

	@Mock
	private PersonDAO personDAO;

	private Person buildPerson(String lastName, String firstName, String address) {
		Person person1 = new Person();
		person1.setFirstName(firstName);
		person1.setLastName(lastName);
		person1.setAddress(address);
		return person1;
	}

	@Test
	void updatePersonTest() {

		// Given
		Person person1 = buildPerson("Oliv", "Topa", "1509 Culver St");
		Person person2 = buildPerson("Gina", "Tata", "1509 Culver St");
		List<Person> persons = new ArrayList<>();
		persons.add(person1);
		persons.add(person2);
		when(personDAO.getAll()).thenReturn(persons);

		Person newPerson = buildPerson("Oliv", "Topa", "11 Bd truc");

		// WHEN
		personService.update(newPerson);

		// THEN

		Assertions.assertThat(persons.get(2).getAddress()).isEqualTo("11 Bd truc");

	}

}
