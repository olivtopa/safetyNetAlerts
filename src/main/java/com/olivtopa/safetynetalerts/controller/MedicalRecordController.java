package com.olivtopa.safetynetalerts.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.olivtopa.safetynetalerts.model.MedicalRecord;
import com.olivtopa.safetynetalerts.service.MedicalRecordService;

@RestController
public class MedicalRecordController {

	@Autowired
	MedicalRecordService medicalRecordService;
	
	private static Logger logger = LoggerFactory.getLogger(MedicalRecordController.class);

	@PostMapping(value = "/medicalRecord")
	public void addMedicalRecord(@RequestBody MedicalRecord newMedicalRecord) {
		
		
logger.info("POST request for add an medical record");
		medicalRecordService.create(newMedicalRecord);
	}

	@RequestMapping(value = "/medicalRecord", method = RequestMethod.GET)
	public List<MedicalRecord> allMedicalRecord() {
		
		logger.info("GET request for have all medical record");
		return medicalRecordService.getAll();
	}

	@PutMapping(value = "/medicalRecord")
	public void update(@RequestBody MedicalRecord newMedicalRecord) {
		
		logger.info("PUT request for modify medical record");
		medicalRecordService.update(newMedicalRecord);
	}

}
