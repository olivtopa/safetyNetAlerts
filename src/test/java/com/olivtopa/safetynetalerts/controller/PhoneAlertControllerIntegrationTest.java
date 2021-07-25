package com.olivtopa.safetynetalerts.controller;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PhoneAlertControllerIntegrationTest {
	
	public static int STATION_NUMBER = 3;
	
	@Autowired
	private PhoneAlertController controller;
	
	@Test
	public void fetch_phones() {
		
		//Given + When
		List<String> phoneAlert = controller.getFindPhoneNumberByFireStationNumber(STATION_NUMBER); 
		
		//Then
		Assertions.assertThat(phoneAlert).isNotEmpty();
		
	}
	

}
