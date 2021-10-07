package com.olivtopa.safetynetalerts.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	private static Logger logger = LoggerFactory.getLogger(ChildAlertController.class);

	@RequestMapping(value = "/childAlert", method = RequestMethod.GET)
	public List<ChildrenList>finalChildrenList(String address){
		logger.info("GET childAlert request for {}", address);
		return childAlertService.childrenList(address);
	}
	
}
