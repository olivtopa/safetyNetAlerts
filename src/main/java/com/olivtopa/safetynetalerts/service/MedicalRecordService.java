package com.olivtopa.safetynetalerts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olivtopa.safetynetalerts.dao.MedicalRecordDAO;
import com.olivtopa.safetynetalerts.model.MedicalRecord;

@Service
public class MedicalRecordService {

	@Autowired
	private MedicalRecordDAO medicalRecordDAO;
	
	public List<MedicalRecord> getAll(){
		return medicalRecordDAO.getAll();
	}
	
	public List<MedicalRecord> create(MedicalRecord NewMedicalRecord){
		List<MedicalRecord> medicalRecord = getAll();
		medicalRecord.add(NewMedicalRecord);
		return medicalRecord;
	}

}
