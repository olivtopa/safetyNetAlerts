package com.olivtopa.safetynetalerts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.olivtopa.safetynetalerts.model.Fire;
import com.olivtopa.safetynetalerts.service.FireService;

@RestController
public class FireController {

	@Autowired
	FireService fireService;

	@RequestMapping(value = "/fire", method = RequestMethod.GET)
	public List<Fire> getInhabitantAndFireStationByAddress(String address) {

		return fireService.inhabitantByAddress(address);

	}

}
