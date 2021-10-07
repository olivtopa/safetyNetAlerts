package com.olivtopa.safetynetalerts.dao;

import com.olivtopa.safetynetalerts.constant.File;
import com.olivtopa.safetynetalerts.controller.EntitiesLoader;
import com.olivtopa.safetynetalerts.model.Entities;
import com.olivtopa.safetynetalerts.model.FiresStation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
public class FireStationDAO {

	@Autowired
	EntitiesLoader entitiesLoader;
	
	private static Logger logger = LoggerFactory.getLogger(FireStationDAO.class);

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
			logger.info("{} is correctly added on fire station list" ,firesStation.toString());
		} catch (IOException e) {
			logger.error("A problem occurred while creating a fire station",e);
			throw new RuntimeException(e);
		}

	}

	public void update(FiresStation firesStation) {
		try {
			Entities entities = entitiesLoader.load(File.FILENAME);
			entities.getFirestations().removeIf(p -> p.getAddress().equals(firesStation.getAddress()));
			entities.getFirestations().add(firesStation);
			entitiesLoader.write(File.FILENAME, entities);
			logger.info("{} is correctly modified on fire station list" ,firesStation.getAddress());
		} catch (IOException e) {
			logger.error("A problem occurred while updating a fire station",e);
			throw new RuntimeException(e);
		}
	}

	public void delete(String address, Integer station) {
		try {
			Entities entities = entitiesLoader.load(File.FILENAME);
			entities.getFirestations().removeIf(p -> p.getAddress().equals(address)
					|| (p.getStation() == (station)));
			entitiesLoader.write(File.FILENAME, entities);
			logger.info("{} {} is correctly deleted on fire station list" ,address,station);
		} catch (IOException e) {
			logger.error("A problem occurred while deleting a fire station",e);
			throw new RuntimeException(e);
		}
	}
}
