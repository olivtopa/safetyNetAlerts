package com.olivtopa.safetynetalerts.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FloodControllerIntegrationTest {

	@Autowired
	private FloodController controller;
	
	/*	@Test
	public void findPersonsInFireStationsList() {
		
		//GIVEN + WHEN
		Object resultat = controller.findPersonsInFireStationList(1,2,3,4);
		
		//THEN
		Assertions.assertThat(resultat).isNotNull();
	}*/
	
}
