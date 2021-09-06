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
		
		//GIVEN
		when(personDAO.getAll()).thenReturn(List.of(new Person()));
		
		// When
	    PersonsInFireStation resultat = firestationService.findPersonsInFireStationScope(1L);

	    // Then
	    Assertions.assertNotNull(resultat);
	    Assertions.assertEquals(0, resultat.getNbAdults());
	    Assertions.assertEquals(0, resultat.getNbChildren());
	    Assertions.assertEquals(1, resultat.getPersons().size());
	}
	
}
