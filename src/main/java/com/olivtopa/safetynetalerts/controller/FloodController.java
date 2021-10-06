package com.olivtopa.safetynetalerts.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.olivtopa.safetynetalerts.service.FloodService;
import com.olivtopa.safetynetalerts.model.FloodAddress;

@RestController
public class FloodController {

	@Autowired
	FloodService floodService;
	
	private static Logger logger = LoggerFactory.getLogger(FloodController.class);

	public FloodController(FloodService floodService) {
		this.floodService = floodService;
	}

	@RequestMapping(value = "/flood/stations", method = RequestMethod.GET)
	public List<FloodAddress> floodListOfFoyer(@RequestParam("stations") List<Integer> stations) {
		logger.info("GET request for list of homes served by the fire station" );
		return floodService.finalFloodList(stations);
	}

}