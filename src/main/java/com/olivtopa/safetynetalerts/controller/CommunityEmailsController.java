package com.olivtopa.safetynetalerts.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommunityEmailsController {
	

	@RequestMapping(value = "/city/emails", method = RequestMethod.GET)
	public List<String> getCommunityEmails(String city) {

		return null;

	}

}
