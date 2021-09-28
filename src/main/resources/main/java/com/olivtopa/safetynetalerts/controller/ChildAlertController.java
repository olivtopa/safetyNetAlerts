package com.olivtopa.safetynetalerts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.olivtopa.safetynetalerts.service.ChildAlertService;
import com.olivtopa.safetynetalerts.model.ChildrenList;

@RestController
public class ChildAlertController {
	
	@Autowired
	ChildAlertService childAlertService;

	@RequestMapping(value = "/childAlert", method = RequestMethod.GET)
	public List<ChildrenList>finalChildrenList(String address){
		return childAlertService.childrenList(address);
	}
	
}
