package com.olivtopa.safetynetalerts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.olivtopa.safetynetalerts.service.FloodService;
import com.olivtopa.safetynetalerts.model.FloodFoyer;

@RestController
public class FloodController {
	
	@Autowired
	FloodService floodService;
	
	public FloodController(FloodService floodService) {
		this.floodService = floodService;
	}

	@RequestMapping(value = "/flood/stations", method = RequestMethod.GET)
	public List<FloodFoyer>floodListOfFoyer(@RequestParam("stations") List<Integer> stations) {
		return floodService.floodFoyerListByStationNumbers(stations);
	}

}