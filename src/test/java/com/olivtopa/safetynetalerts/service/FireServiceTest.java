package com.olivtopa.safetynetalerts.service;

import java.time.LocalDate;
import java.util.List;

//import org.junit.jupiter.api.Assertions;
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
import com.olivtopa.safetynetalerts.model.Fire;
import com.olivtopa.safetynetalerts.model.FiresStation;
import com.olivtopa.safetynetalerts.model.MedicalRecord;
import com.olivtopa.safetynetalerts.model.Person;

@ExtendWith(MockitoExtension.class)
public class FireServiceTest {

	@InjectMocks
	private FireService fireService;

	@Mock
	PersonDAO personDAO;
	@Mock
	FireStationDAO fireStationDAO;
	@Mock
	MedicalRecordDAO medicalRecordDAO;

	@Test
	public void KnownAdressReturnUnhabitant() {

		// GIVEN
		Person person1 = new Person();
		person1.setAddress("address1");
		person1.setFirstName("Oliv");
		person1.setLastName("DUPONT");
		person1.setPhone("0101010101");

		FiresStation firesStation1 = new FiresStation();
		firesStation1.setAddress("address1");
		firesStation1.setStation(1);

		MedicalRecord medicalRecord1 = new MedicalRecord();
		medicalRecord1.setFirstName("Oliv");
		medicalRecord1.setLastName("DUPONT");
		medicalRecord1.setBirthdate(LocalDate.of(2000, 5, 17));

		Person person2 = new Person();
		person2.setAddress("address");
		person2.setFirstName("Oliver");
		person2.setLastName("DUPONTIS");
		person2.setPhone("0202020202");

		FiresStation firesStation2 = new FiresStation();
		firesStation2.setAddress("address");
		firesStation2.setStation(2);

		MedicalRecord medicalRecord2 = new MedicalRecord();
		medicalRecord2.setFirstName("Oliver");
		medicalRecord2.setLastName("DUPONTIS");
		medicalRecord2.setBirthdate(LocalDate.of(2005, 3, 16));

		Mockito.when(personDAO.getAll()).thenReturn(List.of(person1, person2));
		Mockito.when(fireStationDAO.getAll()).thenReturn(List.of(firesStation1, firesStation2));
		Mockito.when(medicalRecordDAO.getAll()).thenReturn(List.of(medicalRecord1, medicalRecord2));

		// WHEN
		List<Fire> result = fireService.inhabitantByAddress("address1");

		// THEN
		Assertions.assertThat(result.get(0).getFirstName()).isEqualTo("Oliv");

	}

	@Test
	public void unKnownAdress() {

		Person person = new Person();
		person.setAddress("address");
		person.setFirstName("Oliver");

		Mockito.when(personDAO.getAll()).thenReturn(List.of(person));

		// WHEN
		List<Fire> firstNameFinded = fireService.inhabitantByAddress("unknown");

		// THEN
		Assertions.assertThat(firstNameFinded).isEmpty();
	}

	@Test
	public void KnownAdressNoInhabitantReturnEmpty() {

		Person person = new Person();
		person.setAddress("address");

		Mockito.when(personDAO.getAll()).thenReturn(List.of());

		// WHEN
		List<Fire> nobodyFound = fireService.inhabitantByAddress("address");

		// THEN
		Assertions.assertThat(nobodyFound).isEmpty();

	}

}