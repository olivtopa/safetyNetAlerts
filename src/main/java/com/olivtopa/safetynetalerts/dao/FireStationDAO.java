package com.olivtopa.safetynetalerts.dao;

import com.olivtopa.safetynetalerts.constant.File;
import com.olivtopa.safetynetalerts.controller.EntitiesLoader;
import com.olivtopa.safetynetalerts.model.Entities;
import com.olivtopa.safetynetalerts.model.FiresStation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
public class FireStationDAO {

	@Autowired
	EntitiesLoader entitiesLoader;

	public List<FiresStation> getAll() {
		try {
			return entitiesLoader.load(File.FILENAME).getFirestations();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void create(FiresStation firesStation) {
		try {
			Entities entities = entitiesLoader.load(File.FILENAME);
			entities.getFirestations().add(firesStation);
			entitiesLoader.write(File.FILENAME, entities);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	public void update(FiresStation firesStation) {
		try {
			EntitiesLoader entitiesLoader = new EntitiesLoader();
			Entities entities = entitiesLoader.load(File.FILENAME);
			entities.getFirestations().removeIf(p -> p.getAddress().equals(firesStation.getAddress()));
			entities.getFirestations().add(firesStation);
			entitiesLoader.write(File.FILENAME, entities);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
