package com.olivtopa.safetynetalerts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.olivtopa.safetynetalerts.model.FiresStation;

@RestController
public class PhoneAlertController {

	@Autowired
	PhoneAlertController phoneAlertService;

	@RequestMapping(value = "/phoneAlert", method = RequestMethod.GET)
	public List<FiresStation> getAll() {
		return phoneAlertService.getAll();
	}
}
