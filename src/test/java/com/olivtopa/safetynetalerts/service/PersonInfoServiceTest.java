package com.olivtopa.safetynetalerts.service;

import java.time.LocalDate;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

import com.olivtopa.safetynetalerts.dao.MedicalRecordDAO;
import com.olivtopa.safetynetalerts.dao.PersonDAO;
import com.olivtopa.safetynetalerts.model.MedicalRecord;
import com.olivtopa.safetynetalerts.model.Person;
import com.olivtopa.safetynetalerts.model.PersonInfo;

@ExtendWith(MockitoExtension.class)
public class PersonInfoServiceTest {

	@InjectMocks
	private PersonInfoService personInfoService;

	@Mock
	PersonDAO personDAO;
	@Mock
	MedicalRecordDAO medicalRecordDAO;

	private Person buildPerson(String firstName, String lastName, String address, String email) {
		Person person1 = new Person();
		person1.setFirstName(firstName);
		person1.setLastName(lastName);
		person1.setAddress(address);
		person1.setEmail(email);
		return person1;
	}

	private MedicalRecord buildMedicalRecord(String firstName, String lastName, int year, int month, int day) {
		MedicalRecord medicalRecord = new MedicalRecord();
		medicalRecord.setFirstName(firstName);
		medicalRecord.setLastName(lastName);
		medicalRecord.setBirthdate(LocalDate.of(year, month, day));
		return medicalRecord;
	}

	@Test
	public void nameKnownReturnPerson() {

		// Given
		Person person1 = buildPerson("Oliv", "Topa", "address1", "email1");
		when(personDAO.getAll()).thenReturn(List.of(person1));

		MedicalRecord medicalRecord1 = buildMedicalRecord("Oliv", "Topa", 2000, 7, 25);
		when(medicalRecordDAO.getAll()).thenReturn(List.of(medicalRecord1));

		// When
		List<PersonInfo> personFound = personInfoService.personDetails("Oliv", "Topa");

		// Then
		Assertions.assertThat(personFound.get(0).getAddress()).isEqualTo("address1");
	}

	@Test
	public void unKnownNameNotReturnPerson() {

		// Given
		Person person1 = buildPerson("Oliv", "Topa", "address1", "email1");
		when(personDAO.getAll()).thenReturn(List.of(person1));

		// When
		List<PersonInfo> personFound = personInfoService.personDetails("Vilo", "Topa");

		// Then
		Assertions.assertThat(personFound).isEmpty();
	}

}
