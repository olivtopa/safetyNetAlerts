package com.olivtopa.safetynetalerts.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.olivtopa.safetynetalerts.service.CommunityEmailsService;

@RestController
public class CommunityEmailsController {
	CommunityEmailsService communityEmailsService = new CommunityEmailsService();
	
	@RequestMapping(value = "/city/emails", method = RequestMethod.GET)
	public List<String> getCommunityEmails(String city) {

		return CommunityEmailsService.getEmails(city);

	}

}
