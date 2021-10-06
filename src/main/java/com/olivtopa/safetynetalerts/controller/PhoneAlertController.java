package com.olivtopa.safetynetalerts.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.olivtopa.safetynetalerts.service.PhoneAlertService;

@RestController
public class PhoneAlertController {

	@Autowired
	PhoneAlertService phoneAlertService;
	
	private static Logger logger = LoggerFactory.getLogger(PhoneAlertController.class);

	@RequestMapping(value = "/phoneAlert", method = RequestMethod.GET)
	public List<String> getFindPhoneNumberByFireStationNumber(Integer firestation) {
		
		logger.info("GET request for phone numbers of people on firestation {} ", firestation);
		
		if(firestation == null) {
			throw new IllegalArgumentException("firestation cannot be null");
		}
		return phoneAlertService.findPhoneNumberByFireStationNumber(firestation);
		
	}
}
