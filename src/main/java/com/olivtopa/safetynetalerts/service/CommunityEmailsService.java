package com.olivtopa.safetynetalerts.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olivtopa.safetynetalerts.model.Person;

@Service
public class CommunityEmailsService {

	@Autowired
	private PersonService personService;

	private String email;
	List<Person> persons = personService.getAll();

	public List<String> getEmails(String city) {
		List<String> emails = persons.stream().filter(c -> c.getCity()==email).map(Person::getEmail).collect(Collectors.toList());
		return emails;
	}
}
