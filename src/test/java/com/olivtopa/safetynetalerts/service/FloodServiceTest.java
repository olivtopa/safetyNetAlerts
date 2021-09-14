package com.olivtopa.safetynetalerts.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class FloodServiceTest {
	
	@InjectMocks
	private FloodService floodService;
	
	@Test
	void findPersonsInFireStationList() {
		
		Object resultat = floodService.findPersonsInFireStationsList(1, 2, 3, 4);
		
		Assertions.assertNotNull(resultat);
	}

}
