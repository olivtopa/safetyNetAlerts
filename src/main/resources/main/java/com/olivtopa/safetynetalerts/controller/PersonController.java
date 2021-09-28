package com.olivtopa.safetynetalerts.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.olivtopa.safetynetalerts.model.Person;
import com.olivtopa.safetynetalerts.service.PersonService;

@RestController
public class PersonController {
	
	private final PersonService personService;
	
	public PersonController(PersonService personService) {
		this.personService = personService;
	}
	
	@RequestMapping(value = "/persons", method = RequestMethod.GET)
	public List<Person> getAll() {
		return personService.getAll();
	}
	
	@PostMapping(value = "/person")
	public List<Person>addPerson(@RequestBody Person newPerson){
		return personService.create(newPerson);
	}

}
