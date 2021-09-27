package com.olivtopa.safetynetalerts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
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

	@PostMapping(value ="/medicalRecord")
	public List<MedicalRecord>addMedicalRecord(@RequestBody MedicalRecord newMedicalRecord){
		
		return medicalRecordService.create(newMedicalRecord);
	}
	
	@RequestMapping(value = "/medicalRecord", method = RequestMethod.GET)
	public List<MedicalRecord> allMedicalRecord(){
		return medicalRecordService.getAll();
	}
	

}
