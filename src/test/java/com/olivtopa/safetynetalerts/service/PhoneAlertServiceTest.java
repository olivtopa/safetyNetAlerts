package com.olivtopa.safetynetalerts.service;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.olivtopa.safetynetalerts.dao.FireStationDAO;
import com.olivtopa.safetynetalerts.dao.PersonDAO;
import com.olivtopa.safetynetalerts.model.FiresStation;
import com.olivtopa.safetynetalerts.model.Person;

@ExtendWith(MockitoExtension.class)
public class PhoneAlertServiceTest {

	@InjectMocks
	private PhoneAlertService phoneAlertService;
	
	
	@Mock FireStationDAO fireStationDAO;
	@Mock PersonDAO personDAO;

	@Test
	public void knownStationNumberReturnPhoneNumber() {
		
		

		// GIVEN
		FiresStation matchingFiresStation = new FiresStation();
		matchingFiresStation.setStation(1);
		matchingFiresStation.setAddress("address1");
		
		Person matchingPerson = new Person();
		matchingPerson.setAddress("address1");
		matchingPerson.setPhone("0101010101");
		
		FiresStation noMatchingFiresStation = new FiresStation();
		noMatchingFiresStation.setStation(2);
		noMatchingFiresStation.setAddress("address2");
		
		Person noMatchingPerson = new Person();
		noMatchingPerson.setAddress("address2");
		noMatchingPerson.setPhone("0202020202");
		
		Mockito.when(fireStationDAO.getAll()).thenReturn(List.of(matchingFiresStation,noMatchingFiresStation));
		Mockito.when(personDAO.getAll()).thenReturn(List.of(matchingPerson,noMatchingPerson));

		// When
		List<String> phoneNumberFinded = phoneAlertService.findPhoneNumberByFireStationNumber(1);

		// Then
		Assertions.assertThat(phoneNumberFinded).containsExactly(matchingPerson.getPhone());
	}

	@Test
	public void unknownStationNumberReturnNoPhoneNumber() {

		// Given
		FiresStation firesStation = new FiresStation();
		firesStation.setStation(1);
		firesStation.setAddress("address1");
		Person person = new Person();
		person.setPhone("0102030405");
		person.setAddress("address1");
		Mockito.when(fireStationDAO.getAll()).thenReturn(List.of(firesStation));

		// When
		List<String> phoneNumberFinded = phoneAlertService.findPhoneNumberByFireStationNumber(3);

		// Then
		Assertions.assertThat(phoneNumberFinded).isEmpty();
	}

	@Test
	public void noStationNumberReturnNoPhoneNumber() {

		// Given
		Mockito.when(fireStationDAO.getAll()).thenReturn(List.of());
		Mockito.when(personDAO.getAll()).thenReturn(List.of());

		// When
		List<String> phoneNumberFinded = phoneAlertService.findPhoneNumberByFireStationNumber(1);

		// Then
		Assertions.assertThat(phoneNumberFinded).isEmpty();

	}
}