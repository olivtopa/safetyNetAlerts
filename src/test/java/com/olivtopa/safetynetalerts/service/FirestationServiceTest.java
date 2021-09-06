package com.olivtopa.safetynetalerts.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;

import com.olivtopa.safetynetalerts.dao.FireStationDAO;
import com.olivtopa.safetynetalerts.dao.MedicalRecordDAO;
import com.olivtopa.safetynetalerts.dao.PersonDAO;
import com.olivtopa.safetynetalerts.model.FiresStation;
import com.olivtopa.safetynetalerts.model.MedicalRecord;
import com.olivtopa.safetynetalerts.model.Person;
import com.olivtopa.safetynetalerts.model.PersonsInFireStation;

@ExtendWith(MockitoExtension.class)
class FirestationServiceTest {

	@InjectMocks
	private FirestationService firestationService;

	@Mock
	private PersonDAO personDAO;
	
	@Mock
    private FireStationDAO fireStationDAO;
	
	@Mock
	private MedicalRecordDAO medicalRecordDAO;

	@Test
	void findPersonsInFireStationScope() {

	    // Given
	    Person person1 = buildPerson("Durand", "Léo", "1509 Culver St");
	    Person person2 = buildPerson("Durand", "Christelle", "1509 Culver St");
	    Person outOfFireStationPerson = buildPerson("Donovan", "Thibaut", "892 Downing Ct");
	    when(personDAO.getAll()).thenReturn(List.of(person1, person2, outOfFireStationPerson));

	    FiresStation fireStation = new FiresStation();
	    fireStation.setStation(1);
	    fireStation.setAddress("1509 Culver St");
	    when(fireStationDAO.getAll()).thenReturn(List.of(fireStation));

	    MedicalRecord medicalRecordLeo = buildMedicalRecord("Léo", "Durand", 2005, 3, 16);
	    MedicalRecord medicalRecordChristelle = buildMedicalRecord("Christelle", "Durand", 1981, 9, 22);
	    when(medicalRecordDAO.getAll()).thenReturn(List.of(medicalRecordLeo, medicalRecordChristelle));

	    // When
	    PersonsInFireStation resultat = firestationService.findPersonsInFireStationScope(1L);

	    // Then
	    Assertions.assertNotNull(resultat);
	    Assertions.assertEquals(1, resultat.getNbAdults());
	    Assertions.assertEquals(1, resultat.getNbChildren());
	    Assertions.assertEquals(2, resultat.getPersons().size());
	    Assertions.assertEquals("Léo", resultat.getPersons().get(0).getFirstName());
	    Assertions.assertEquals("Durand", resultat.getPersons().get(0).getLastName());
	    Assertions.assertEquals(LocalDate.of(2005, 3, 16), resultat.getPersons().get(0).getBirthDate());
	    Assertions.assertEquals("Christelle", resultat.getPersons().get(1).getFirstName());
	    Assertions.assertEquals("Durand", resultat.getPersons().get(1).getLastName());
	}

	private Person buildPerson(String lastName, String firstName, String address) {
	    Person person1 = new Person();
	    person1.setFirstName(firstName);
	    person1.setLastName(lastName);
	    person1.setAddress(address);
	    return person1;
	}

	private MedicalRecord buildMedicalRecord(String firstName, String lastName, int year, int month, int day) {
	    MedicalRecord medicalRecord = new MedicalRecord();
	    medicalRecord.setFirstName(firstName);
	    medicalRecord.setLastName(lastName);
	    medicalRecord.setBirthdate(LocalDate.of(year, month, day));
	    return medicalRecord;
	}
}

