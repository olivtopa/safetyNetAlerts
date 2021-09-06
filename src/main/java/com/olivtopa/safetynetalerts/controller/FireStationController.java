package com.olivtopa.safetynetalerts.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.olivtopa.safetynetalerts.model.Fire;

@RestController
public class FireStationController {
	
	@RequestMapping(value = "/firestation", method = RequestMethod.GET)
	public List<Fire>findPersonsinFireStationScope(long stationNumber){
		return null;
		
	}

}
