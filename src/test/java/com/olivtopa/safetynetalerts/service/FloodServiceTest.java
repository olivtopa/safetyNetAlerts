package com.olivtopa.safetynetalerts.service;

import java.time.LocalDate;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.assertj.core.groups.Tuple;
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
import com.olivtopa.safetynetalerts.model.FloodFoyer;

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
	public void adressReturnFoyer() {

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

		Mockito.when(personDAO.getAll()).thenReturn(List.of(person, person2));
		Mockito.when(medicalRecordDAO.getAll()).thenReturn(List.of(medicalRecord1, medicalRecord2));
		Mockito.when(fireStationDAO.getAll()).thenReturn(List.of(firesStation));
		// WHEN
	/*	List<String> result = floodService.floodFoyerListByStationNumbers(1);

		// THEN
		Assertions.assertThat(result)
				.extracting(FloodFoyer::getFirstName, FloodFoyer::getLastName, FloodFoyer::getPhone)
				.contains(new Tuple("Oliv", "DUPONT", "11 22 33 44 55"), new Tuple("Gil", "DUPONT", "22 33 44 55 66"));
*/
	}

}
