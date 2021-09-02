package com.olivtopa.safetynetalerts.service;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.olivtopa.safetynetalerts.dao.FireStationDAO;
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
	private PersonService personService;
	
	@Mock PersonDAO personDAO;
	@Mock FireStationDAO fireStationDAO;
	@Mock MedicalRecordDAO medicalRecordDAO;
	
	@Test
	public void NameKnownReturnPerson( ) {
		
		//Given
		Person person = new Person();
		person.setFirstName("Oliv");
		person.setLastName("Topa");
		person.setAddress("address1");
		person.setEmail("email1");
		
		
		
		MedicalRecord medicalRecord = new MedicalRecord();
		medicalRecord.setBirthdate(05/06/2000);
		medicalRecord.setMedications(null);
		medicalRecord.setAllergies(null);
		
		Mockito.when(personService.getAll()).thenReturn(List.of(person, medicalRecord));
		
		//When
		List<PersonInfo> personFound = personInfoService.personDetails("Oliv","Topa");
		
		//Then
		Assertions.assertThat(personFound).containsExactly(person.getFirstName());
		Assertions.assertThat(personFound).containsExactly(person.getLastName());
		Assertions.assertThat(personFound).containsExactly(person.getAddress());
		Assertions.assertThat(personFound).containsExactly(person.getEmail());
	}
	
	@Test
	public void unknownFirstNameReturnNoPerson( ) {

		// Given
		Person person = new Person();
		person.setFirstName("Oliv");
		person.setLastName("Topa");
		Mockito.when(personService.getAll()).thenReturn(List.of(person));

		// When
		List<PersonInfo> personFound = personInfoService.personDetails("Topi","Topa");

		// Then
		Assertions.assertThat(personFound).isEmpty();
	}
	
	@Test
	public void unknownLastNameReturnNoPerson( ) {

		// Given
		Person person = new Person();
		person.setFirstName("Oliv");
		person.setLastName("Topa");
		Mockito.when(personService.getAll()).thenReturn(List.of(person));

		// When
		List<PersonInfo> personFound = personInfoService.personDetails("Oliv","Popa");

		// Then
		Assertions.assertThat(personFound).isEmpty();
	}
	

}
