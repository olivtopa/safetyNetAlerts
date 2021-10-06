package com.olivtopa.safetynetalerts.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	private static Logger logger = LoggerFactory.getLogger(FireStationController.class);

	public FireStationController(FirestationService firestationService) {
		this.firestationService = firestationService;
	}

	@RequestMapping(value = "/firestation", method = RequestMethod.GET)
	public PersonsInFireStation findPersonsInFireStationScope(long stationNumber) {
		
		logger.info("GET request for person by firestation");
		return firestationService.findPersonsInFireStationScope(stationNumber);
	}

	@PostMapping(value = "/firestation")
	public void addAStation(@RequestBody FiresStation newFiresStation) {
		logger.info("POST request for create a firestation");
		firestationService.create(newFiresStation);
	}

	@PutMapping(value = "/firestation")
	public void update(@RequestBody FiresStation newFiresStation) {
		logger.info("PUT request for modify a firestation");
		firestationService.update(newFiresStation);
	}

	@DeleteMapping(value = "/firestation")
	  public void deletePerson(@RequestParam(required = false) String address,
	      @RequestParam(required = false) Integer station) {
		logger.info("DELETE request for delete a firestation");
		firestationService.delete(address, station);
	  }
}
