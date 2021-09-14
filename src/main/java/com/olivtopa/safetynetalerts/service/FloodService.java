package com.olivtopa.safetynetalerts.service;

import org.springframework.stereotype.Service;

import com.olivtopa.safetynetalerts.model.PersonsInFirestationList;

@Service
public class FloodService {
	
	public Object findPersonsInFireStationsList(long firestationNumber1,long firestationNumber2,
			long firestationNumber3,long firestationNumber4) {
		return new PersonsInFirestationList();
	}

}
