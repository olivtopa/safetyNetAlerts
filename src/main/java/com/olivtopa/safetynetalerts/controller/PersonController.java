package com.olivtopa.safetynetalerts.controller;

import java.util.List;

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

	public PersonController(PersonService personService) {
		this.personService = personService;
	}

	@RequestMapping(value = "/persons", method = RequestMethod.GET)
	public List<Person> getAll() {
		return personService.getAll();
	}

	@PostMapping(value = "/person")
	public void addPerson(@RequestBody Person newPerson) {
		personService.create(newPerson);
	}

	@PutMapping(value = "/person")
	public void updatePerson(@RequestBody Person newPerson) {
		personService.update(newPerson);

	}
	
	@DeleteMapping(value = "/person")
	public void deletePerson(@RequestParam String firstName, @RequestParam String lastName){
	
		personService.delete(firstName,lastName);
	}
}

