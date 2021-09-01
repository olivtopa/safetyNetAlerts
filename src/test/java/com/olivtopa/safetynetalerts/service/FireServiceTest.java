package com.olivtopa.safetynetalerts.service;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fasterxml.jackson.annotation.JsonFormat;
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
	
	@Mock PersonDAO personDAO;
	@Mock FireStationDAO fireStationDAO;
	@Mock MedicalRecordDAO medicalRecordDAO;
	
/*	@Test
	public void KnownAdressReturnUnhabitant() {
		
		//GIVEN
		Person matchingPerson = new Person();
		matchingPerson.setAddress("address1");
		matchingPerson.setFirstName("Oliv");
		matchingPerson.setLastName("DUPONT");
		matchingPerson.setPhone("0101010101");
		
		FiresStation matchingFiresStation = new FiresStation();
		matchingFiresStation.setAddress("address1");
		matchingFiresStation.setStation(1);
		
		MedicalRecord matchingMedicalRecord = new MedicalRecord();
		matchingMedicalRecord.setFirstName("Oliv");
		matchingMedicalRecord.setLastName("DUPONT");
		
		//@JsonFormat(pattern = "MM/dd/yyyy")
		//matchingMedicalRecord.setBirthdate(03/06/2000);
		//matchingMedicalRecord.setMedications(["aznol:350mg", "hydrapermazol:100mg"]);
		//matchingMedicalRecord.setAllergies(["peanut", "shellfish", "aznol"]);
		
		Person nomatchingPerson = new Person();
		nomatchingPerson.setAddress("address2");
		nomatchingPerson.setFirstName("Oliver");
		nomatchingPerson.setLastName("DUPONTIS");
		nomatchingPerson.setPhone("0202020202");
		
		FiresStation nomatchingFiresStation = new FiresStation();
		nomatchingFiresStation.setAddress("address2");
		nomatchingFiresStation.setStation(2);
		
		MedicalRecord nomatchingMedicalRecord = new MedicalRecord();
		nomatchingMedicalRecord.setFirstName("Oliver");
		nomatchingMedicalRecord.setLastName("DUPONTIS");
		//nomatchingMedicalRecord.setBirthdate(03/05/1990);
		//nomatchingMedicalRecord.setMedications([]);
		//nomatchingMedicalRecord.setAllergies(["peanut"]);
		
		Mockito.when(personDAO.getAll()).thenReturn(List.of(matchingPerson,nomatchingPerson));
		Mockito.when(fireStationDAO.getAll()).thenReturn(List.of(matchingFiresStation,nomatchingFiresStation));
		Mockito.when(medicalRecordDAO.getAll()).thenReturn(List.of(matchingMedicalRecord,nomatchingMedicalRecord));
		
		//WHEN
		List<Fire> firstNameFinded = fireService.inhabitantByAddress("address1");
		
		//THEN
		Assertions.assertThat(firstNameFinded).containsExactly(matchingPerson.getFirstName());
		
	}*/
	
	@Test
	public void unKnownAdress() {
		
		Person person = new Person();
		person.setAddress("address");
		person.setFirstName("Oliver");
		
		Mockito.when(personDAO.getAll()).thenReturn(List.of(person));
		
		//WHEN
		List<Fire> firstNameFinded = fireService.inhabitantByAddress("unknown");
		
		//THEN
		Assertions.assertThat(firstNameFinded).isEmpty();
	}
	
	
	
	
	@Test
	public void KnownAdressNoInhabitantReturnEmpty() {
		
		Person person = new Person();
		person.setAddress("address");
				
		Mockito.when(personDAO.getAll()).thenReturn(List.of());
		
		//WHEN
		List<Fire> nobodyFound = fireService.inhabitantByAddress("address");
		
		//THEN
		Assertions.assertThat(nobodyFound).isEmpty();
		
	}

}