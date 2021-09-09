package com.olivtopa.safetynetalerts.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ChildAlertServiceTest {
	
	@InjectMocks
	private ChildAlertService childAlertService;
	
	@Test
	void finalChildrenList() {
		
		Object resultat = childAlertService.finalChildrenList("1509 Culver St");
		
		Assertions.assertNotNull(resultat);
	}

}
