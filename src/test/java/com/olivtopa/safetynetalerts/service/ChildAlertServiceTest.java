package com.olivtopa.safetynetalerts.service;

import java.time.LocalDate;
import java.util.List;

import org.assertj.core.api.Assertions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.olivtopa.safetynetalerts.dao.MedicalRecordDAO;
import com.olivtopa.safetynetalerts.dao.PersonDAO;
import com.olivtopa.safetynetalerts.model.PersonList;
import com.olivtopa.safetynetalerts.model.MedicalRecord;
import com.olivtopa.safetynetalerts.model.Person;

@ExtendWith(MockitoExtension.class)
public class ChildAlertServiceTest {

	@InjectMocks
	private ChildAlertService childAlertService;

	@Mock
	PersonDAO personDAO;
	@Mock
	MedicalRecordDAO medicalRecordDAO;

	@Test
	public void finalChildrenList() {

		// GIVEN
		Person person1 = new Person();
		person1.setAddress("address1");
		person1.setFirstName("Oliv");
		person1.setLastName("DUPONT");

		MedicalRecord medicalRecord1 = new MedicalRecord();
		medicalRecord1.setFirstName("Oliv");
		medicalRecord1.setLastName("DUPONT");
		medicalRecord1.setBirthdate(LocalDate.of(2000, 5, 17));

		Mockito.when(personDAO.getAll()).thenReturn(List.of(person1));
		Mockito.when(medicalRecordDAO.getAll()).thenReturn(List.of(medicalRecord1));
		// WHEN
		List<PersonList> result = childAlertService.finalChildrenList("address1");

		// THEN
		Assertions.assertThat(result.contains(person1));
	}
}