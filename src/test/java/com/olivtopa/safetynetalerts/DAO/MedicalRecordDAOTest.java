package com.olivtopa.safetynetalerts.DAO;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.olivtopa.safetynetalerts.controller.EntitiesLoader;
import com.olivtopa.safetynetalerts.dao.MedicalRecordDAO;
import com.olivtopa.safetynetalerts.model.Entities;
import com.olivtopa.safetynetalerts.model.MedicalRecord;


@ExtendWith(MockitoExtension.class)
public class MedicalRecordDAOTest {

	@InjectMocks
	MedicalRecordDAO medicalRecordDAO;

	@Mock
	EntitiesLoader entitiesLoader;
	
	@Test
	public void create() throws IOException {

		// Given
		Entities entities = new Entities();
		entities.setMedicalrecords(new ArrayList<>());
		Mockito.when(entitiesLoader.load(ArgumentMatchers.anyString())).thenReturn(entities);

		// When
		MedicalRecord medicalRecordUpdated = new MedicalRecord();;
		medicalRecordUpdated.setFirstName("Oliv");
		medicalRecordUpdated.setLastName("Topa");
		medicalRecordUpdated.setBirthdate(LocalDate.of(2014, 5, 17));

		medicalRecordDAO.create(medicalRecordUpdated);

		// Then
		Assertions.assertThat(entities.getMedicalrecords()).first().usingRecursiveComparison().isEqualTo(medicalRecordUpdated);
	}

	@Test
	public void update() throws IOException {
		// Given
		Entities entities = new Entities();
		MedicalRecord medicalRecord = new MedicalRecord();
		medicalRecord.setFirstName("Oliv");
		medicalRecord.setLastName("Topa");
		medicalRecord.setBirthdate(LocalDate.of(2000, 8, 20));
		List<MedicalRecord> medicalrecords = new ArrayList<>();
		medicalrecords.add(medicalRecord);
		entities.setMedicalrecords(medicalrecords);
		Mockito.when(entitiesLoader.load(ArgumentMatchers.anyString())).thenReturn(entities);

		// When
		MedicalRecord medicalRecordUpdated = new MedicalRecord();
		medicalRecordUpdated.setFirstName(medicalRecord.getFirstName());
		medicalRecordUpdated.setLastName(medicalRecord.getLastName());
		medicalRecordUpdated.setBirthdate(LocalDate.of(2014, 5, 17));
		
		medicalRecordDAO.update(medicalRecordUpdated);

		// Then
		Assertions.assertThat(entities.getMedicalrecords()).first().extracting(MedicalRecord::getBirthdate)
				.isEqualTo(medicalRecordUpdated.getBirthdate());
	}

}
