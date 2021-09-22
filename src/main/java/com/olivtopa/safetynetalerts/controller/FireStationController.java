package com.olivtopa.safetynetalerts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.olivtopa.safetynetalerts.dao.FireStationDAO;
import com.olivtopa.safetynetalerts.model.FiresStation;
import com.olivtopa.safetynetalerts.model.PersonsInFireStation;
import com.olivtopa.safetynetalerts.service.FirestationService;

@RestController
public class FireStationController {

	@Autowired
	FirestationService firestationService;
	@Autowired
	FireStationDAO fireStationDAO;

	public FireStationController(FirestationService firestationService) {
		this.firestationService = firestationService;
	}

	@RequestMapping(value = "/firestation", method = RequestMethod.GET)
	public PersonsInFireStation findPersonsInFireStationScope(long stationNumber) {
		return firestationService.findPersonsInFireStationScope(stationNumber);
	}
	
	@PostMapping(value = "/firestation")
	public void addAStation(@RequestBody FiresStation newFiresStation) {
		fireStationDAO.create(newFiresStation);
	}

}
