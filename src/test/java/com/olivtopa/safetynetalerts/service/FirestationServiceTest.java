package com.olivtopa.safetynetalerts.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

import java.util.List;

import com.olivtopa.safetynetalerts.dao.PersonDAO;
import com.olivtopa.safetynetalerts.model.Person;
import com.olivtopa.safetynetalerts.model.PersonsInFireStation;

@ExtendWith(MockitoExtension.class)
class FirestationServiceTest {

	@InjectMocks
	private FirestationService firestationService;

	@Mock
	private PersonDAO personDAO;

	@Test
	void findPersonInFireStationScope() {

		// GIVEN
		Person person1 = new Person();
		person1.setFirstName("Léo"); // Cette valeur doit se retrouver dans le résultat
		person1.setLastName("Durand"); // Celle-ci également
		person1.setAddress("1509 Culver St");
		when(personDAO.getAll()).thenReturn(List.of(person1));

		// When
		PersonsInFireStation resultat = firestationService.findPersonsInFireStationScope(1L);

		// Then
		Assertions.assertNotNull(resultat);
		Assertions.assertEquals(0, resultat.getNbAdults());
		Assertions.assertEquals(0, resultat.getNbChildren());
		Assertions.assertEquals(1, resultat.getPersons().size());
		Assertions.assertEquals("Léo", resultat.getPersons().get(0).getFirstName());
		Assertions.assertEquals("Durand", resultat.getPersons().get(0).getLastName());
	}

}
