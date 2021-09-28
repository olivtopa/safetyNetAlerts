package com.olivtopa.safetynetalerts.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FireStationControllerIntegrationTest {

	@Autowired
	private FireStationController controller;

	@Test
	public void findPersonsInFireStationScope() {

		// GIVEN + WHEN
		Object resultat = controller.findPersonsInFireStationScope(1);

		// THEN
		Assertions.assertThat(resultat).isNotNull(); // vérification très haut niveau.

	}

}
