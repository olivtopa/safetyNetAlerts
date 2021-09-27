package com.olivtopa.safetynetalerts.dao;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.olivtopa.safetynetalerts.constant.File;
import com.olivtopa.safetynetalerts.controller.EntitiesLoader;
import com.olivtopa.safetynetalerts.model.Entities;
import com.olivtopa.safetynetalerts.model.MedicalRecord;

@Repository
public class MedicalRecordDAO {

	@Autowired
	EntitiesLoader entitiesLoader;

	public List<MedicalRecord> getAll() {
		try {
			return entitiesLoader.load(File.FILENAME).getMedicalrecords();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void create(MedicalRecord medicalRecord) {
		try {
			EntitiesLoader entitiesLoader = new EntitiesLoader();
			Entities entities = entitiesLoader.load(File.FILENAME);
			entities.getMedicalrecords().add(medicalRecord);

			entitiesLoader.write(File.FILENAME, entities);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}
}
