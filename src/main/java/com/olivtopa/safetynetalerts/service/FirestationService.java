package com.olivtopa.safetynetalerts.service;

import org.springframework.stereotype.Service;

import com.olivtopa.safetynetalerts.model.PersonsInFireStation;

@Service
public class FirestationService {
	
	public PersonsInFireStation findPersonsInFireStationScope(long stationNumber) {
		return new PersonsInFireStation();
	}

}
