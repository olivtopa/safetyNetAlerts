package com.olivtopa.safetynetalert.dao;

import java.util.List;

import org.assertj.core.api.Assertions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.olivtopa.safetynetalerts.dao.FireStationDAO;

import com.olivtopa.safetynetalerts.model.FiresStation;

@ExtendWith(MockitoExtension.class)
public class FirestationDAOTest {

	@Mock
	FireStationDAO fireStationDAO;

	@Test
	public void creatAnewFireStationTest() {

		// GIVEN
		FiresStation firesStation = new FiresStation();
		firesStation.setAddress("address1");
		firesStation.setStation(1);

		FiresStation newFiresStation = new FiresStation();
		newFiresStation.setAddress("address2");
		newFiresStation.setStation(2);

		Mockito.when(fireStationDAO.getAll()).thenReturn(List.of(firesStation));

		// WHEN
		FiresStation newFireStation = fireStationDAO.create(newFiresStation);

		// THEN
		Assertions.assertThat(newFireStation).extracting(FiresStation::getAddress, FiresStation::getStation)
				.containsExactly("address2", "2");

	}

}
