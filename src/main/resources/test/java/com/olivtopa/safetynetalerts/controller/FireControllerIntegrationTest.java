package com.olivtopa.safetynetalerts.controller;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.olivtopa.safetynetalerts.model.Fire;

@SpringBootTest
public class FireControllerIntegrationTest {
	
	public static String ADDRESS = "1509 Culver St";
	
	@Autowired
	private FireController controller;
	
	@Test
	public void inhabitantByAddress() {
		
		//GIVEN + WHEN
		List<Fire> fire = controller.getInhabitantAndFireStationByAddress(ADDRESS);
		
		//THEN
		Assertions.assertThat(fire).isNotEmpty();
	}
	

}
