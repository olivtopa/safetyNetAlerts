package com.olivtopa.safetynetalerts.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.olivtopa.safetynetalerts.model.PersonInfo;

@SpringBootTest
public class PersonInfoControllerIntegrationTest {

	public static String FIRSTNAME = "John";
	public static String LASTNAME = "Boyd";

	@Autowired
	private PersonInfoController controller;

	@Test
	public void inhabitantByName() {

		// GIVEN + WHEN
		PersonInfo personInfo = controller.returnPersonInfo(FIRSTNAME, LASTNAME);

		// THEN
		Assertions.assertThat(personInfo).isNotNull();
	}

}
