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
	private EntitiesLoader entitiesLoader;

	public List<MedicalRecord> getAll() {
		try {
			return entitiesLoader.load(File.FILENAME).getMedicalrecords();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void create(MedicalRecord medicalRecord) {
		try {
			Entities entities = entitiesLoader.load(File.FILENAME);
			entities.getMedicalrecords().add(medicalRecord);

			entitiesLoader.write(File.FILENAME, entities);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	public void update(MedicalRecord medicalRecord) {
		try {

			Entities entities = entitiesLoader.load(File.FILENAME);
			entities.getMedicalrecords().removeIf(m -> m.getFirstName().equals(medicalRecord.getFirstName())
					&& m.getLastName().equals(medicalRecord.getLastName()));
			entities.getMedicalrecords().add(medicalRecord);
			entitiesLoader.write(File.FILENAME, entities);
		} catch (IOException e) {
			throw new RuntimeException(e);

		}
	}
}
