package com.olivtopa.safetynetalerts.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.olivtopa.safetynetalerts.model.PersonsInFireStation;
import com.olivtopa.safetynetalerts.service.FirestationService;

@RestController
public class FireStationController {

	private final FirestationService firestationService;

	public FireStationController(FirestationService firestationService) {
		this.firestationService = firestationService;
	}

	@RequestMapping(value = "/firestation", method = RequestMethod.GET)
	public PersonsInFireStation findPersonsInFireStationScope(long stationNumber) {
		return firestationService.findPersonsInFireStationScope(stationNumber);
	}

}
