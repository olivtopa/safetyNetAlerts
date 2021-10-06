package com.olivtopa.safetynetalerts.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.olivtopa.safetynetalerts.service.CommunityEmailsService;

@RestController
public class CommunityEmailsController {
	@Autowired
	CommunityEmailsService communityEmailsService;
	private static Logger logger = LoggerFactory.getLogger(CommunityEmailsController.class);

	@RequestMapping(value = "/communityEmail", method = RequestMethod.GET)
	public List<String> getCommunityEmails(String city) {
		logger.info("GET request for emails by city");
		return communityEmailsService.getEmails(city);

	}

}
