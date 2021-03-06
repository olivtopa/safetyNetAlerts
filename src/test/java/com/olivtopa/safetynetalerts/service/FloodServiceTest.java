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

import com.olivtopa.safetynetalerts.dao.FireStationDAO;
import com.olivtopa.safetynetalerts.dao.MedicalRecordDAO;
import com.olivtopa.safetynetalerts.dao.PersonDAO;
import com.olivtopa.safetynetalerts.model.MedicalRecord;
import com.olivtopa.safetynetalerts.model.Person;
import com.olivtopa.safetynetalerts.model.FiresStation;
import com.olivtopa.safetynetalerts.model.FloodPerson;
import com.olivtopa.safetynetalerts.model.FloodAddress;

@ExtendWith(MockitoExtension.class)
public class FloodServiceTest {
	@InjectMocks
	private FloodService floodService;

	@Mock
	PersonDAO personDAO;
	@Mock
	MedicalRecordDAO medicalRecordDAO;

	@Mock
	FireStationDAO fireStationDAO;

	@Test
	public void addressByFireStationNumberTest() {

		// GIVEN
		Person person = new Person();
		person.setFirstName("Oliv");
		person.setLastName("DUPONT");
		person.setAddress("address1");
		person.setPhone("11 22 33 44 55");

		MedicalRecord medicalRecord1 = new MedicalRecord();
		medicalRecord1.setFirstName("Oliv");
		medicalRecord1.setLastName("DUPONT");
		medicalRecord1.setBirthdate(LocalDate.of(2000, 5, 17));

		FiresStation firesStation = new FiresStation();
		firesStation.setAddress("address1");
		firesStation.setStation(1);

		Person person2 = new Person();
		person2.setFirstName("Gil");
		person2.setLastName("DUPONT");
		person2.setAddress("address1");
		person2.setPhone("22 33 44 55 66");

		MedicalRecord medicalRecord2 = new MedicalRecord();
		medicalRecord2.setFirstName("Gil");
		medicalRecord2.setLastName("DUPONT");
		medicalRecord2.setBirthdate(LocalDate.of(2010, 8, 20));

		FiresStation firesStation1 = new FiresStation();
		firesStation1.setAddress("address2");
		firesStation1.setStation(1);

		Person person3 = new Person();
		person3.setFirstName("Polo");
		person3.setLastName("ROGER");
		person3.setAddress("address2");
		person3.setPhone("22 33 44 55 77");

		MedicalRecord medicalRecord3 = new MedicalRecord();
		medicalRecord3.setFirstName("Polo");
		medicalRecord3.setLastName("ROGER");
		medicalRecord3.setBirthdate(LocalDate.of(2010, 8, 20));

		FiresStation firesStation2 = new FiresStation();
		firesStation2.setAddress("address3");
		firesStation2.setStation(2);

		Mockito.when(fireStationDAO.getAll()).thenReturn(List.of(firesStation, firesStation1, firesStation2));

		// WHEN
		List<String> resultat = floodService.addressByFireStationNumber(1);

		// THEN
		Assertions.assertThat(resultat).containsExactly("address1", "address2");

	}

	@Test
	public void floodPersonCreationTest() {

		// GIVEN
		Person person = new Person();
		person.setFirstName("Oliv");
		person.setLastName("DUPONT");
		person.setAddress("address1");
		person.setPhone("11 22 33 44 55");

		MedicalRecord medicalRecord1 = new MedicalRecord();
		medicalRecord1.setFirstName("Oliv");
		medicalRecord1.setLastName("DUPONT");
		medicalRecord1.setBirthdate(LocalDate.of(2000, 5, 17));

		FiresStation firesStation = new FiresStation();
		firesStation.setAddress("address1");
		firesStation.setStation(1);

		Person person2 = new Person();
		person2.setFirstName("Gil");
		person2.setLastName("DUPONT");
		person2.setAddress("address1");
		person2.setPhone("22 33 44 55 66");

		MedicalRecord medicalRecord2 = new MedicalRecord();
		medicalRecord2.setFirstName("Gil");
		medicalRecord2.setLastName("DUPONT");
		medicalRecord2.setBirthdate(LocalDate.of(2010, 8, 20));

		FiresStation firesStation1 = new FiresStation();
		firesStation1.setAddress("address2");
		firesStation1.setStation(1);

		Person person3 = new Person();
		person3.setFirstName("Polo");
		person3.setLastName("ROGER");
		person3.setAddress("address2");
		person3.setPhone("22 33 44 55 77");

		MedicalRecord medicalRecord3 = new MedicalRecord();
		medicalRecord3.setFirstName("Polo");
		medicalRecord3.setLastName("ROGER");
		medicalRecord3.setBirthdate(LocalDate.of(2010, 8, 20));

		FiresStation firesStation2 = new FiresStation();
		firesStation2.setAddress("address3");
		firesStation2.setStation(2);

		// WHEN
		FloodPerson floodPerson = floodService.buildFloodPerson(person, medicalRecord1);

		// THEN
		Assertions.assertThat(floodPerson).extracting(FloodPerson::getFirstName, FloodPerson::getLastName)
				.containsExactly("Oliv", "DUPONT");

	}

	@Test
	public void finalFloodListTest() {

		// GIVEN
		Person person = new Person();
		person.setFirstName("Oliv");
		person.setLastName("DUPONT");
		person.setAddress("address1");
		person.setPhone("11 22 33 44 55");

		MedicalRecord medicalRecord1 = new MedicalRecord();
		medicalRecord1.setFirstName("Oliv");
		medicalRecord1.setLastName("DUPONT");
		medicalRecord1.setBirthdate(LocalDate.of(2000, 5, 17));

		FiresStation firesStation = new FiresStation();
		firesStation.setAddress("address1");
		firesStation.setStation(1);

		Person person2 = new Person();
		person2.setFirstName("Gil");
		person2.setLastName("DUPONT");
		person2.setAddress("address1");
		person2.setPhone("22 33 44 55 66");

		MedicalRecord medicalRecord2 = new MedicalRecord();
		medicalRecord2.setFirstName("Gil");
		medicalRecord2.setLastName("DUPONT");
		medicalRecord2.setBirthdate(LocalDate.of(2010, 8, 20));

		FiresStation firesStation1 = new FiresStation();
		firesStation1.setAddress("address2");
		firesStation1.setStation(1);

		Person person3 = new Person();
		person3.setFirstName("Polo");
		person3.setLastName("ROGER");
		person3.setAddress("address2");
		person3.setPhone("22 33 44 55 77");

		MedicalRecord medicalRecord3 = new MedicalRecord();
		medicalRecord3.setFirstName("Polo");
		medicalRecord3.setLastName("ROGER");
		medicalRecord3.setBirthdate(LocalDate.of(2010, 8, 20));

		FiresStation firesStation2 = new FiresStation();
		firesStation2.setAddress("address3");
		firesStation2.setStation(2);

		Mockito.when(fireStationDAO.getAll()).thenReturn(List.of(firesStation, firesStation1, firesStation2));

		// WHEN
		List<FloodAddress> resultat = floodService.finalFloodList(List.of(1, 2));
		// THEN
		Assertions.assertThat(resultat).extracting(FloodAddress::getAddress).containsExactly("address1", "address2",
				"address3");

	}
}
