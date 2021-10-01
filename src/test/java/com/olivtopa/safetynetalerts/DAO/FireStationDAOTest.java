package com.olivtopa.safetynetalerts.DAO;

import java.io.IOException;
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
import com.olivtopa.safetynetalerts.dao.FireStationDAO;

import com.olivtopa.safetynetalerts.model.Entities;
import com.olivtopa.safetynetalerts.model.FiresStation;
import com.olivtopa.safetynetalerts.model.Person;

@ExtendWith(MockitoExtension.class)
public class FireStationDAOTest {

	@InjectMocks
	FireStationDAO fireStationDAO;

	@Mock
	EntitiesLoader entitiesLoader;

	@Test
	public void create() throws IOException {

		// Given
		Entities entities = new Entities();
		entities.setFirestations(new ArrayList<>());
		Mockito.when(entitiesLoader.load(ArgumentMatchers.anyString())).thenReturn(entities);

		// When
		FiresStation fireStationUpdated = new FiresStation();
		fireStationUpdated.setAddress("Address1");
		fireStationUpdated.setStation(1);

		fireStationDAO.create(fireStationUpdated);

		// Then
		Assertions.assertThat(entities.getFirestations()).first().usingRecursiveComparison()
				.isEqualTo(fireStationUpdated);
	}

	@Test
	public void update() throws IOException {

		// Given
		Entities entities = new Entities();
		FiresStation firesStation = new FiresStation();
		firesStation.setAddress("Address1");
		firesStation.setStation(1);
		List<FiresStation> firestations = new ArrayList<>();
		firestations.add(firesStation);
		entities.setFirestations(firestations);
		Mockito.when(entitiesLoader.load(ArgumentMatchers.anyString())).thenReturn(entities);

		// When
		FiresStation fireStationUpdated = new FiresStation();
		fireStationUpdated.setAddress(firesStation.getAddress());
		fireStationDAO.update(fireStationUpdated);

		// Then
		Assertions.assertThat(entities.getFirestations()).first().extracting(FiresStation::getStation)
				.isEqualTo(fireStationUpdated.getStation());
	}
	
	@Test
	public void delete() throws IOException {
		
		//GIVEN
		Entities entities = new Entities();
		FiresStation fireStation = new FiresStation();
		fireStation.setAddress("Address1");
		fireStation.setStation(8);
		List<FiresStation> firestations = new ArrayList<>();
		firestations.add(fireStation);
		entities.setFirestations(firestations);
		Mockito.when(entitiesLoader.load(ArgumentMatchers.anyString())).thenReturn(entities);
		
		//WHEN
		FiresStation fireStationDeleted = new FiresStation();
		fireStationDeleted.setAddress(fireStation.getAddress());
		fireStationDeleted.setStation(fireStation.getStation());
		
		fireStationDAO.delete("Address1", 8);
		
		//THEN
		Assertions.assertThat(entities.getFirestations()).isEmpty();
	}
}
