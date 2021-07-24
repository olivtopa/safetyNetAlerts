package com.olivtopa.safetynetalerts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.olivtopa.safetynetalerts.service.PhoneAlertService;

@RestController
public class PhoneAlertController {

	@Autowired
	PhoneAlertService phoneAlertService;

	@RequestMapping(value = "/phoneAlert", method = RequestMethod.GET)
	public List<String> getFindPhoneNumberByFireStationNumber(int fireStationNumber) {
		return phoneAlertService.findPhoneNumberByFireStationNumber(fireStationNumber);
	}
}
