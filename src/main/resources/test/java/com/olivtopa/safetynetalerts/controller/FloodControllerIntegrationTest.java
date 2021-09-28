package com.olivtopa.safetynetalerts.controller;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.olivtopa.safetynetalerts.model.FloodAddress;

@SpringBootTest
public class FloodControllerIntegrationTest {

	@Autowired
	private FloodController controller;
	
	@Test
	public void findPersonsInFireStationsList() {
		
		//GIVEN + WHEN
		List<FloodAddress> resultat = controller.floodListOfFoyer(List.of(1,2,3,4));
		
		//THEN
		Assertions.assertThat(resultat).isNotNull();
	}
	
}
