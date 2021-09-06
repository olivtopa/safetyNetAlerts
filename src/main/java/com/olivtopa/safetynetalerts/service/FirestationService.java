package com.olivtopa.safetynetalerts.service;

import org.springframework.stereotype.Service;

import com.olivtopa.safetynetalerts.model.PersonsInFireStation;

@Service
public class FirestationService {
	
	public Object findPersonsInFireStationScope(long stationNumber) {
		return new PersonsInFireStation();
	}

}
