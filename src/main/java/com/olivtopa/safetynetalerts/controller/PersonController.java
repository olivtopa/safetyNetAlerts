package com.olivtopa.safetynetalerts.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.olivtopa.safetynetalerts.model.Person;
import com.olivtopa.safetynetalerts.service.PersonService;

@RestController
public class PersonController {

	private final PersonService personService;
	private static Logger logger = LoggerFactory.getLogger(PersonController.class);

	public PersonController(PersonService personService) {
		this.personService = personService;
	}

	@RequestMapping(value = "/persons", method = RequestMethod.GET)
	public List<Person> getAll() {
		logger.info("GET request for all persons");
		return personService.getAll();
	}

	@PostMapping(value = "/person")
	public void addPerson(@RequestBody Person newPerson) {
		logger.info("POST request for add persons");
		personService.create(newPerson);
	}

	@PutMapping(value = "/person")
	public void updatePerson(@RequestBody Person newPerson) {
		logger.info("PUT request for modify persons");
		personService.update(newPerson);

	}
	
	@DeleteMapping(value = "/person")
	public void deletePerson(@RequestParam String firstName, @RequestParam String lastName){
		logger.info("DELETE request for erase a person");
		personService.delete(firstName,lastName);
	}
}

