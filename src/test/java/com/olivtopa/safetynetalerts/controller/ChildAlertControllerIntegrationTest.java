package com.olivtopa.safetynetalerts.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ChildAlertControllerIntegrationTest {

	@Autowired
	private ChildAlertController controller;

	@Test
	public void finalChildrenListTest() {

		// GIVEN + WHEN
		Object resultat = controller.finalChildrenList("1509 Culver St");
		
		//THEN
		Assertions.assertThat(resultat).isNotNull();
	}

}
