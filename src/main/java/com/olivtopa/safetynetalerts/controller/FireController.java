package com.olivtopa.safetynetalerts.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private static Logger logger = LoggerFactory.getLogger(FireController.class);

	@RequestMapping(value = "/fire", method = RequestMethod.GET)
	public List<Fire> getInhabitantAndFireStationByAddress(String address) {

		logger.info("GET Request for persons and firestation for {}", address);

		return fireService.inhabitantByAddress(address);

	}

}
