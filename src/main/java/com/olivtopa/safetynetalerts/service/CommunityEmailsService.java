package com.olivtopa.safetynetalerts.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import com.olivtopa.safetynetalerts.model.Person;

@Service
public class CommunityEmailsService {
	private static Logger logger = LoggerFactory.getLogger(CommunityEmailsService.class);

	@Autowired
	private PersonService personService;

	public List<String> getEmails(String city) {
		logger.info("emails from all residents of: {} ",city);
		List<String> emails = personService.getAll().stream().filter(c -> c.getCity().equals(city))
				.map(Person::getEmail).collect(Collectors.toList());
		return emails;
	}
}
