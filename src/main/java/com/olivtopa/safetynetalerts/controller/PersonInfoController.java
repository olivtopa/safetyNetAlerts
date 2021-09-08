package com.olivtopa.safetynetalerts.controller;




import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.olivtopa.safetynetalerts.model.PersonInfo;
import com.olivtopa.safetynetalerts.service.PersonInfoService;

@RestController
public class PersonInfoController {
	
	@Autowired
	PersonInfoService personInfoService;
	
	@RequestMapping(value = "/personInfo", method = RequestMethod.GET)
	public List<PersonInfo> returnPersonInfo(String firstName, String lastName){
		
		return personInfoService.personDetails(firstName, lastName);
	}

}
