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

	public List<MedicalRecord> getAll() {
		return medicalRecordDAO.getAll();
	}

	public void create(MedicalRecord NewMedicalRecord) {
		medicalRecordDAO.create(NewMedicalRecord);

	}

	public void update(MedicalRecord NewMedicalRecord) {
		medicalRecordDAO.update(NewMedicalRecord);

	}
}
