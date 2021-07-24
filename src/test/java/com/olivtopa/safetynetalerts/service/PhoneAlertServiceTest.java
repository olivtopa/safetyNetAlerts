package com.olivtopa.safetynetalerts.service;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.olivtopa.safetynetalerts.model.FiresStation;
import com.olivtopa.safetynetalerts.model.Person;

@ExtendWith(MockitoExtension.class)
public class PhoneAlertServiceTest {

	@InjectMocks
	private PhoneAlertService phoneAlertService;

	@Test
	public void knownStationNumberReturnPhoneNumber() {

		// GIVEN
		FiresStation findStation = new FiresStation();
		findStation.setStation(3);
		Person findPhone = new Person();
		findPhone.setPhone("0145522336");
		Mockito.when(phoneAlertService.findPhoneNumberByFireStationNumber(3)).thenReturn(List.of("0145522336"));

		// When
		List<String> phoneNumberFinded = phoneAlertService.findPhoneNumberByFireStationNumber(3);

		// Then
		Assertions.assertThat(phoneNumberFinded).containsExactly(findPhone.getPhone());
	}

	@Test
	public void unknownStationNumberReturnNoPhoneNumber() {

		// Given
		FiresStation firestation = new FiresStation();
		firestation.setStation(15);
		Person person = new Person();
		person.setPhone("0102030405");
		Mockito.when(phoneAlertService.findPhoneNumberByFireStationNumber(0)).thenReturn(List.of());

		// When
		List<String> phoneNumberFinded = phoneAlertService.findPhoneNumberByFireStationNumber(3);

		// Then
		Assertions.assertThat(phoneNumberFinded).isEmpty();
	}

	@Test
	public void noStationNumberReturnNoPhoneNumber() {

		// Given
		Mockito.when(phoneAlertService.findPhoneNumberByFireStationNumber(0)).thenReturn(List.of());

		// When
		List<String> phoneNumberFinded = phoneAlertService.findPhoneNumberByFireStationNumber(3);

		// Then
		Assertions.assertThat(phoneNumberFinded).isEmpty();

	}
}