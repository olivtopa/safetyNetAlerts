package com.olivtopa.safetynetalerts.controller;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.olivtopa.safetynetalerts.model.PersonInfo;

@SpringBootTest
public class PersonInfoServiceControllerIntegrationTest {
	
	public static String FIRSTNAME = "John";
	public static String LASTNAME = "Boyd";
	
	@Autowired
	private PersonInfoController controller;
	
	@Test
	public void inhabitantByName() {
		
		// GIVEN + WHEN
		List<PersonInfo> personInfo = controller.getPersonInfo(FIRSTNAME, LASTNAME);
		
		//THEN
		Assertions.assertThat(personInfo).isNotEmpty();
	}

}
