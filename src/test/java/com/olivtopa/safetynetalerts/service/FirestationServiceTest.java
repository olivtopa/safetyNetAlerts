package com.olivtopa.safetynetalerts.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.olivtopa.safetynetalerts.model.PersonsInFireStation;

@ExtendWith(MockitoExtension.class)
class FirestationServiceTest {
	
	@InjectMocks
	private FirestationService firestationService;
	
	@Test
	void findPersonInFireStationScope() {
		
		PersonsInFireStation resultat = firestationService.findPersonsInFireStationScope(1L);
		
		Assertions.assertNotNull(resultat);
		Assertions.assertEquals(0, resultat.getNbAdults());
	}

}
